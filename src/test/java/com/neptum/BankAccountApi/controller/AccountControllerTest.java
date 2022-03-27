package com.neptum.BankAccountApi.controller;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neptum.BankAccountApi.Controller.AccountController;
import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.DTO.request.CardRequest;
import com.neptum.BankAccountApi.DTO.request.CardTypeRquest;
import com.neptum.BankAccountApi.Model.Account;
import com.neptum.BankAccountApi.Service.AccountService;
import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.exception.AccountNotFoundException;
import com.neptum.BankAccountApi.utils.Utilities;

@DisplayName("Test Controller Layer")
@WebMvcTest(controllers = AccountController.class)
public class AccountControllerTest 
{
	@MockBean
	private AccountService accountService;
	@InjectMocks
	private AccountController accountController;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("Should return create status when an account is created")
	@Test
	void shouldReturnCreatedStatusWhenCreateAccount() {
		CardTypeRquest cardTypeRequest = Utilities.generateCardTypeRequest(Constants.DEBIT_CARD);
		CardRequest cardRequest = Utilities.generateCardRequest(
			"CARD TEST", Constants.MASTERCARD, cardTypeRequest, "123.456.789.012", "12345", 1000.0);
		List<CardRequest> cardsRequest = Utilities.generateCardsRequest(cardRequest);
		AccountRequest accountRequest = Utilities.generateAccountRequest(
			"ACCOUNT TEST", "1234", "12345678", "1", "123.456.789.01", cardsRequest);
		Account account = Account.generateAccountInstance(accountRequest);
		
		Mockito
			.when(accountService.createAccount(accountRequest))
			.thenReturn(account);
		
		ResponseEntity<Account> createdAccount = accountController.createAccount(accountRequest);
		
		Assertions.assertEquals(ResponseEntity.class, createdAccount.getClass());
		Assertions.assertEquals(HttpStatus.CREATED, createdAccount.getStatusCode());
		Assertions.assertEquals(accountRequest.getNameOwner(), createdAccount.getBody().getNameOwner());
	}
	
	@DisplayName("should return ok status when retrieve all accounts")
	@Test
	void shouldReturnOkStatusWhenGetAccounts() {
		CardTypeRquest firstCardTypeRequest = Utilities.generateCardTypeRequest(Constants.DEBIT_CARD);
		List<CardRequest> firstCardsRequest = Utilities.generateCardsRequest(
			new CardRequest("CARD TEST", Constants.MASTERCARD, firstCardTypeRequest, "123.456.789.012", "12345", 1000.0));
		AccountRequest firstAccountRequest = Utilities.generateAccountRequest(
			"ACCOUNT TEST", "1234", "12345678", "1", "123.456.789.01", firstCardsRequest);
		
		CardTypeRquest secondCardTypeRequest = Utilities.generateCardTypeRequest(Constants.CREDIT_CARD);
		List<CardRequest> secondCardsRequest = Utilities.generateCardsRequest(
			new CardRequest("CARD TEST", Constants.MASTERCARD, secondCardTypeRequest, "123.456.789.012", "12345", 1000.0));
		AccountRequest secondAccountRequest = Utilities.generateAccountRequest(
			"ACCOUNT TEST", "1234", "12345678", "1", "123.456.789.01", secondCardsRequest);
		
		List<AccountRequest> accountsRequest = Utilities.generateAccountsRequest(firstAccountRequest, secondAccountRequest);
		List<Account> accounts = Account.generateAccountsInstance(accountsRequest);
		
		Mockito
			.when(accountService.getAccounts())
			.thenReturn(accounts);
		
		ResponseEntity<List<Account>> retrievedAccounts = accountController.getAccounts();
		
		Assertions.assertEquals(HttpStatus.OK, retrievedAccounts.getStatusCode());
	}
	
	@DisplayName("Should return ok status when retrieve accunt by id")
	@Test
	void shouldReturnOkStatusWhenGetAccountById() {
		CardTypeRquest cardTypeRequest = Utilities.generateCardTypeRequest(Constants.DEBIT_CARD);
		CardRequest cardRequest = Utilities.generateCardRequest(
			"CARD TEST", Constants.MASTERCARD, cardTypeRequest, "123.456.789.012", "12345", 1000.0);
		List<CardRequest> cardsRequest = Utilities.generateCardsRequest(cardRequest);
		AccountRequest accountRequest = Utilities.generateAccountRequest(
			"ACCOUNT TEST", "1234", "12345678", "1", "123.456.789.01", cardsRequest);
		Account account = Account.generateAccountInstance(accountRequest);
		Integer id = Integer.valueOf(1);
		account.setId(id);
		
		Mockito
			.when(accountService.getAccountById(id))
			.thenReturn(account);
		
		ResponseEntity<Account> retrieveAccount = accountController.getAccountById(id);
		
		Assertions.assertEquals(HttpStatus.OK, retrieveAccount.getStatusCode());
		Assertions.assertEquals(id, retrieveAccount.getBody().getId());
	}
	
	@DisplayName("Should throw exception when account id not found")
	@Test
	void shouldReturnBadRequestWhenAccountIdIsNotFoud() {
		int anyInt = ArgumentMatchers.anyInt();
		
		Mockito
			.when(accountService.getAccountById(anyInt))
			.thenThrow(new AccountNotFoundException(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
		
		try {
			accountController.getAccountById(anyInt);
		} catch (AccountNotFoundException ex) {
			Assertions.assertEquals(Constants.ACCOUNT_NOT_FOUND_EXCEPTION, ex.getMessage());
		}
		
	}
}

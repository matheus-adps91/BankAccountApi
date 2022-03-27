package com.neptum.BankAccountApi.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.DTO.request.CardRequest;
import com.neptum.BankAccountApi.DTO.request.CardTypeRquest;
import com.neptum.BankAccountApi.Model.Account;
import com.neptum.BankAccountApi.Model.Card;
import com.neptum.BankAccountApi.Model.CardType;
import com.neptum.BankAccountApi.Model.Type;
import com.neptum.BankAccountApi.Repository.AccountRepository;
import com.neptum.BankAccountApi.Repository.CardRepository;
import com.neptum.BankAccountApi.Repository.CardTypeRepository;
import com.neptum.BankAccountApi.Service.AccountService;
import com.neptum.BankAccountApi.Service.TaskCollaborator;
import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.exception.AccountNotFoundException;
import com.neptum.BankAccountApi.utils.Utilities;

@DisplayName("Test Service Layer")
@TestInstance(Lifecycle.PER_CLASS)
public class AccountServiceTest
{
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private CardRepository cardRepository;
	@Mock
	private CardTypeRepository cardTypeRepository;	
	@Mock
	private TaskCollaborator taskCollaborator;
	@InjectMocks
	private AccountService accountService;
	
	@BeforeEach
	void init() { 
		MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("Should create an account successfully")
	@Test
	void shouldCreateAccuntSuccessfully() throws Exception {
		CardTypeRquest cardTypeRquest = Utilities.generateCardTypeRequest(Constants.DEBIT_CARD);
		CardRequest cardRequest = Utilities.generateCardRequest(
			"CARD TEST", Constants.MASTERCARD, cardTypeRquest, "123.456.789.012", "12345", 1000.0);
		List<CardRequest> cardsRequest = Utilities.generateCardsRequest(cardRequest);
		AccountRequest accountRequest = Utilities.generateAccountRequest(
			"ACCOUNT TEST", "1234", "12345678", "1", "123.456.789.01", cardsRequest);
		Account account = Account.generateAccountInstance(accountRequest);
		
		Set<Type> types = new HashSet<>();
		types.add(Type.valueOf(Constants.DEBIT_CARD));
		Set<CardType> cardsTypes = new HashSet<>();
		cardsTypes.add(new CardType(Integer.valueOf(1), Constants.DEBIT_CARD));
		
		Mockito
			.when(cardTypeRepository.findAllByTypeIn(types))
			.thenReturn(cardsTypes);
		Mockito
			.when(taskCollaborator.findIdForTypeName(Constants.MASTERCARD, cardsTypes))
			.thenReturn(Integer.valueOf(1));
		Mockito
			.when(accountRepository.save(ArgumentMatchers.any()))
			.thenReturn(account);
		
		Account savedAccount = accountService.createAccount(accountRequest);
		
		Assertions.assertNotNull(savedAccount);
		Assertions.assertAll("account",
				() -> assertEquals(accountRequest.getNameOwner(), savedAccount.getNameOwner()),
				() -> assertEquals(accountRequest.getAgencyCode() , savedAccount.getAgencyCode()),
				() -> assertEquals(accountRequest.getAccountCode(), savedAccount.getAccountCode()),
				() -> assertEquals(accountRequest.getVerificationDigital(), savedAccount.getVerificationDigital())
		);
		Assertions.assertAll(
				() -> {
						Card card = savedAccount.getCards().get(0);
						
						assertAll("card",
							() -> assertEquals(cardRequest.getName(), card.getName()),
							() -> assertEquals(cardRequest.getFlag(), card.getFlag().name()),
							() -> assertEquals(cardRequest.getNumber(), card.getNumber()),
							() -> assertEquals(cardRequest.getDigitCode(), card.getDigitCode()),
							() -> assertEquals(cardRequest.getLimitBalance(), card.getLimitBalance())
						);
				}
		);
	}
	
	@DisplayName("Should return all instances")
	@Test
	void shouldReturnAllInstances() {
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
			.when(accountRepository.findAll())
			.thenReturn(accounts);
		
		List<Account> retrievedAccounts = accountService.getAccounts();
		
		Assertions.assertNotNull(retrievedAccounts);
		Assertions.assertEquals(accountsRequest.size(), retrievedAccounts.size());
	}
	
	@DisplayName("Should return account by Id")
	@Test
	void shouldReturnAccountById() {
		CardTypeRquest cardTypeRequest = Utilities.generateCardTypeRequest(Constants.DEBIT_CARD);
		List<CardRequest> cardsRequest = Utilities.generateCardsRequest(
				new CardRequest("CARD TEST", Constants.MASTERCARD, cardTypeRequest, "123.456.789.012", "12345", 1000.0));
		AccountRequest accountRequest = Utilities.generateAccountRequest(
				"ACCOUNT TEST", "1234", "12345678", "1", "123.456.789.01", cardsRequest);
		Account account = Account.generateAccountInstance(accountRequest);
		Integer id = Integer.valueOf(1);
		account.setId(id);
		Optional<Account> oAccount = Optional.of(account);
		
		Mockito
			.when(accountRepository.findById(id))
			.thenReturn(oAccount);
		
		Account retrievedAccount = accountService.getAccountById(id);
		
		Assertions.assertNotNull(retrievedAccount);
		Assertions.assertEquals(id, retrievedAccount.getId());
	}
	
	@DisplayName("Should throw account not found exception")
	@Test
	void shouldThrowAccountNotFoundException() {		
		Optional<Account> oAccount = Optional.empty();
		int anyInt = ArgumentMatchers.anyInt();
		
		Mockito
			.when(accountRepository.findById(anyInt))
			.thenReturn(oAccount);
		
		AccountNotFoundException accountNotFound = Assertions.assertThrows(
			AccountNotFoundException.class, () -> accountService.getAccountById(anyInt));
		
		assertEquals(Constants.ACCOUNT_NOT_FOUND_EXCEPTION, accountNotFound.getMessage());
	}
	
	@DisplayName("Should delete an account successfully")
	@Test
	void shouDeleteAnAccountSuccessfully() {
		Account account = mock(Account.class);
		Optional<Account> oAccount = Optional.of(account);
		int anyInt = ArgumentMatchers.anyInt();
		
		Mockito
			.when(accountRepository.findById(anyInt))
			.thenReturn(oAccount);
		
		accountService.deleteAccountById(anyInt);
		Mockito.verify(accountRepository).delete(account);
	}
	
	@DisplayName("Should update an account successfully")
	@Test
	void shouldUpdateAccountById() {			
		Account account = Mockito.mock(Account.class);
		Optional<Account> oAccount = Optional.of(account);
		
		List<CardRequest> newCardsRequest = Utilities.generateCardsRequest(
			new CardRequest("CARD UPDATE", Constants.VISA, new CardTypeRquest(Constants.CREDIT_CARD), "987.654.321.098", "98765", 2000.0));
		AccountRequest newAccountRequest = Utilities.generateAccountRequest(
			"UPDATE TEST", "9876", "98765432", "9", "987.654.321.09", newCardsRequest);
		Account newAccount = Account.generateAccountInstance(newAccountRequest);
		int anyInt = ArgumentMatchers.anyInt();
		
		Set<Type> types = new HashSet<>();
		types.add(Type.valueOf(Constants.CREDIT_CARD));
		Set<CardType> cardsTypes = new HashSet<>();
		cardsTypes.add(new CardType(Integer.valueOf(2), Constants.CREDIT_CARD));
		
		Mockito
			.when(accountRepository.findById(anyInt))
			.thenReturn(oAccount);
		
		Mockito
			.when(cardTypeRepository.findAllByTypeIn(types))
			.thenReturn(cardsTypes);
	
		Mockito
			.when(accountRepository.save(ArgumentMatchers.any()))
			.thenReturn(newAccount);
		
		Account updateAccount = accountService.updateAccountById(anyInt, newAccountRequest);
		Assertions.assertNotNull(updateAccount);
		Assertions.assertAll("account",
				() -> assertEquals(newAccountRequest.getNameOwner(), updateAccount.getNameOwner()),
				() -> assertEquals(newAccountRequest.getAgencyCode() , updateAccount.getAgencyCode()),
				() -> assertEquals(newAccountRequest.getAccountCode(), updateAccount.getAccountCode()),
				() -> assertEquals(newAccountRequest.getVerificationDigital(), updateAccount.getVerificationDigital())
		);
		Assertions.assertAll(
				() -> {
						CardRequest newCardRequest = newAccountRequest.getCardsRequest().get(0);
						Card updatedCard = updateAccount.getCards().get(0);
						
						assertAll("card",
							() -> assertEquals(newCardRequest.getName(), updatedCard.getName()),
							() -> assertEquals(newCardRequest.getFlag(), updatedCard.getFlag().name()),
							() -> assertEquals(newCardRequest.getNumber(), updatedCard.getNumber()),
							() -> assertEquals(newCardRequest.getDigitCode(), updatedCard.getDigitCode()),
							() -> assertEquals(newCardRequest.getLimitBalance(), updatedCard.getLimitBalance())
						);
				}
		);
		
	}
}
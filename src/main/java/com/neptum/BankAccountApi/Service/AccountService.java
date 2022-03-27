package com.neptum.BankAccountApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.Model.Account;
import com.neptum.BankAccountApi.Model.Card;
import com.neptum.BankAccountApi.Model.CardType;
import com.neptum.BankAccountApi.Model.Type;
import com.neptum.BankAccountApi.Repository.AccountRepository;
import com.neptum.BankAccountApi.Repository.CardRepository;
import com.neptum.BankAccountApi.Repository.CardTypeRepository;
import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.exception.AccountNotFoundException;

@Service
public class AccountService
{
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private CardTypeRepository cardTypeRepository;
	
	public Account createAccount(
		final AccountRequest accountRequest) 
	{
		final TaskCollaborator taskCollaborator = new TaskCollaborator();
		final Set<String> cardsNames = taskCollaborator.getCardsTypesNames(accountRequest);
		final Set<Type> types = taskCollaborator.getTypes(cardsNames);
		final Set<CardType> persistedCardsTypes = cardTypeRepository.findAllByTypeIn(types);
		final Account account = Account.generateAccountInstance(accountRequest);
		taskCollaborator.setIdForEachCardType(persistedCardsTypes, account);
		final Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}
	
	public List<Account> getAccounts() { return accountRepository.findAll(); }

	public Account getAccountById(
		final Integer id) 
	{
		final Optional<Account> oAccount = accountRepository.findById(id);
		if (!oAccount.isPresent()) {
			throw new AccountNotFoundException(Constants.ACCOUNT_NOT_FOUND_EXCEPTION);
		}
		final Account account = oAccount.get();
		return account;
	}
	
	public void deleteAccountById(
		final Integer id) 
	{
		final Account account = getAccountById(id);
		accountRepository.delete(account);
	}
	
	public Account updateAccountById(
		final Integer id, 
		final AccountRequest accountRequest) 
	{
		final TaskCollaborator taskCollaborator = new TaskCollaborator();
		final Account account = getAccountById(id);
		final Set<String> cardsNames = taskCollaborator.getCardsTypesNames(accountRequest);
		final Set<Type> types = taskCollaborator.getTypes(cardsNames);
		final Set<CardType> persistedCardsTypes = cardTypeRepository.findAllByTypeIn(types);
		final Account newAccount = Account.generateAccountInstance(accountRequest);
		taskCollaborator.setIdForEachCardType(persistedCardsTypes, newAccount);
		newAccount.setId(account.getId());
		final Account updatedAccount = accountRepository.save(newAccount);
		deleteOldCardsEntities(account.getCards());
		return updatedAccount; 
	}

	private void deleteOldCardsEntities(
		final List<Card> cards) 
	{
		cardRepository.deleteAll(cards);
	}
}
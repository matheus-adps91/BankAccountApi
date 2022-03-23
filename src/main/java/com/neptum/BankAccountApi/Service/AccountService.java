package com.neptum.BankAccountApi.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.MoreCollectors;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.Model.Account;
import com.neptum.BankAccountApi.Model.Card;
import com.neptum.BankAccountApi.Model.CardType;
import com.neptum.BankAccountApi.Model.Type;
import com.neptum.BankAccountApi.Repository.AccountRepository;
import com.neptum.BankAccountApi.Repository.CardTypeRepository;
import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.exception.AccountNotFoundException;

@Service
public class AccountService
{
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CardTypeRepository cardTypeRepository;
	
	public Account createAccount(
		final AccountRequest accountRequest) 
	{
		final Set<String> cardsNames = getCardsNames(accountRequest);
		final Set<Type> types = getCardsTypes(cardsNames);
		final Set<CardType> persistedCardsTypes = cardTypeRepository.findAllByTypeIn(types);
		
		final Account account = new Account(
			accountRequest.getNameOwner(), 
			accountRequest.getAgencyCode(), 
			accountRequest.getAccountCode(), 
			accountRequest.getVerificationDigital(),
			accountRequest.getRegisterId(),
			accountRequest.getCardsRequest());
				
		for (final Card card : account.getCards()) {			
			final String name = card.getCardType().getType().getName();
			final Integer id = findIdForType(name, persistedCardsTypes);
			card.getCardType().setId(id);
		}
			
		final Account savedAccount = accountRepository.save(account);
		return savedAccount;
	}
	
	public List<Account> getAccounts() 
	{
		return accountRepository.findAll();
	}

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
	
	private Integer findIdForType(
		final String name, 
		final Set<CardType> persistedCardsTypes) 
	{
		return persistedCardsTypes.stream()
			.filter(cardType -> cardType.getType().getName().equals(name))
			.map(cardType -> cardType.getId())
			.collect(MoreCollectors.onlyElement());
	}

	private Set<Type> getCardsTypes(
		final Set<String> cardsNames) 
	{
		 return	cardsNames.stream()
			.map(cardName -> Type.valueOf(cardName.toUpperCase()))
			.collect(Collectors.toSet());
	}

	private Set<String> getCardsNames(
		final AccountRequest accountRequest) 
	{
		return accountRequest.getCardsRequest().stream()
			.map(cardRequest -> cardRequest.getCardTypeRequest().getCardType())
			.collect(Collectors.toSet());
	}
}
package com.neptum.BankAccountApi.Service;

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
				
		for (Card card : account.getCards()) {			
			Integer id = findIdForType(card.getCardType().getType().getName(), persistedCardsTypes);
			card.getCardType().setId(id);
		}
			
		final Account savedAccount = accountRepository.save(account);
		return savedAccount;
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
		 return	cardsNames
			.stream()
			.map( cardName -> Type.valueOf(cardName))
			.collect(Collectors.toSet());
	}

	private Set<String> getCardsNames(
		final AccountRequest accountRequest) 
	{
		return accountRequest.getCardsRequest()
			.stream()
			.map( cardRequest -> cardRequest.getCardTypeRequest().getCardType())
			.collect(Collectors.toSet());
	}
}
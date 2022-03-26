package com.neptum.BankAccountApi.Service;

import java.util.Set;
import java.util.stream.Collectors;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.Model.Account;
import com.neptum.BankAccountApi.Model.Card;
import com.neptum.BankAccountApi.Model.CardType;
import com.neptum.BankAccountApi.Model.Type;
import com.neptum.BankAccountApi.constants.Constants;

public class TaskCollaborator
{
	public Integer findIdForTypeName(
		final String searchedName, 
		final Set<CardType> persistedCardsTypes) 
	{
		for (final CardType cardType : persistedCardsTypes) {
			final String name = cardType.getType().name();
			if ( name.equals(searchedName)) {
				return Integer.valueOf( cardType.getId());
			}
		}
		return Constants.INSTANCE_NOT_FOUND;
	}
	
	public Set<Type> getTypes(
		final Set<String> cardsNames) 
	{
		 return	cardsNames.stream()
			.map(cardName -> Type.valueOf(cardName.toUpperCase()))
			.collect(Collectors.toSet());
	}

	public Set<String> getCardsTypesNames(
		final AccountRequest accountRequest) 
	{
		return accountRequest.getCardsRequest().stream()
			.map(cardRequest -> cardRequest.getCardTypeRequest().getTypeName())
			.collect(Collectors.toSet());
	}
	
	public void setIdForEachCardType(
		final Set<CardType> persistedCardsTypes, 
		final Account account) 
	{
		for (final Card card : account.getCards()) {			
			final String name = card.getCardType().getType().name();
			final Integer cardTypeId = findIdForTypeName(name, persistedCardsTypes);
			if ( cardTypeId == Constants.INSTANCE_NOT_FOUND ) {
				throw new RuntimeException(Constants.CARD_TYPE_ID_NOT_FOUND_EXCEPTION);
			}
			card.getCardType().setId(cardTypeId);
		}
	}
}
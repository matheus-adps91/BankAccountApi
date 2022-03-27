package com.neptum.BankAccountApi.utils;

import java.util.Arrays;
import java.util.List;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.DTO.request.CardRequest;
import com.neptum.BankAccountApi.DTO.request.CardTypeRquest;

public class Utilities
{
	public static CardTypeRquest generateCardTypeRequest(
		final String cardTypeName) 
	{
		return new CardTypeRquest(cardTypeName);
	}
	
	public static List<CardRequest> generateCardsRequest(
		final CardRequest... cardsRequests) 
	{
		return Arrays.asList(cardsRequests);
	}
	
	public static CardRequest generateCardRequest(
		final String name,
		final String flag,
		final CardTypeRquest cardTypeRequest,
		final String number,
		final String digitCode,
		final Double limitBalance) 
	{
		return new CardRequest(
			name, flag, cardTypeRequest, number, digitCode, limitBalance);
	}
	
	public static List<CardRequest> generatedCardsRequest(
		final CardRequest... cardsRequest)
	{
		return Arrays.asList(cardsRequest);
	}

	public static AccountRequest generateAccountRequest(
		final String nameOwner, 
		final String agencyCode,
		final String accountCode, 
		final String verificationDigital, 
		final String registerId,
		final List<CardRequest> cardsRequest) 
	{
		return new AccountRequest(
			nameOwner, agencyCode, accountCode, verificationDigital, registerId, cardsRequest);
	}

	public static List<AccountRequest> generateAccountsRequest(
			AccountRequest... accountsRequest) 
	{
		return Arrays.asList(accountsRequest);
	}
}
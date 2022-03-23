package com.neptum.BankAccountApi.DTO.request;

import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.customValidation.CardTypeConstraint;

public class CardTypeRquest
{
	@CardTypeConstraint(acceptedValues = {
		Constants.DEBIT_CARD, Constants.CREBIT_CARD, Constants.GIFT_CARD, Constants.MEAL_CARD })
	private String cardType;

	public String getCardType() { return cardType; }
}
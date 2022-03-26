package com.neptum.BankAccountApi.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.customValidation.CardTypeConstraint;

public class CardTypeRquest
{
	@JsonProperty(value = "type")
	@CardTypeConstraint(acceptedValues = {
		Constants.DEBIT_CARD, Constants.CREDIT_CARD, Constants.GIFT_CARD, Constants.MEAL_CARD })
	private String typeName;

	public CardTypeRquest() { }

	public CardTypeRquest(
		final String cardType) 
	{
		this.typeName = cardType;
	}
	
	public String getTypeName() { return typeName; }
}
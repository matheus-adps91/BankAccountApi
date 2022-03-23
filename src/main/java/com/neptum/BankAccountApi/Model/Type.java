package com.neptum.BankAccountApi.Model;

public enum Type
{
	DEBIT_CARD("DEBIT_CARD"),
	CREDIT_CARD("CREDIT_CARD"),
	MEAL_CARD("MEAL_CARD"),
	GIFT_CARD("GIFT_CARD");
	
	private final String name;

	Type(String type) {
		this.name = type.toUpperCase();
	}

	public String getName() { return name; }
	
}
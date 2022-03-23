package com.neptum.BankAccountApi.constants;

public class Constants 
{
	// Validation
	public static final String NAME_OWNER_NULL_CONSTRAINT = "Name owner is mandatory";
	public static final String AGENCY_CODE_NULL_CONSTRAINT = "Agency code is mandatory";
	public static final String ACCOUNT_CODE_NULL_CONSTRAINT = "Account code is mandatory";
	public static final String VERIFICATION_DIGITAL_NULL_CONSTRAINT = "Verification Digital is mandatory";
	public static final String REGISTER_ID_NULL_CONSTRAINT = "Register id is mandatory";

	public static final String NAME_OWNER_SIZE_CONSTRAINT = "Name owner max character size 50";
	public static final String AGENCY_CODE_SIZE_CONSTRAINT = "Agency code max character size 4";
	public static final String ACCOUNT_CODE_SIZE_CONSTRAINT = "Account code max character size 8";
	public static final String VERIFICATION_DIGITAL_SIZE_CONSTRAINT = "Verification digital owner max character size 1";
	public static final String REGISTER_ID_SIZE_CONSTRAINT = "Register id max character size 20";
	
	public static final String CARD_NAME_NULL_CONSTRAINT = "Card name is mandatory";
	public static final String CARD_NUMBER_NULL_CONSTRAINT = "Card number is mandatory";
	public static final String CARD_DIGIT_CODE_NULL_CONSTRAINT = "Card digit code is mandatory";
	
	public static final String CARD_NAME_SIZE_CONSTRAINT = "Card name max character size 50";
	public static final String CARD_NUMBER_SIZE_CONSTRAINT = "Card number max character size 20";
	public static final String CARD_DIGIT_SIZE_CONSTRAINT = "Card digit code max character size 5";
	
	// Exception
	public static final String ACCOUNT_NOT_FOUND_EXCEPTION = "Account not found!";

	// Card Type
	public static final String DEBIT_CARD = "DEBIT_CARD";
	public static final String CREBIT_CARD = "CREDIT_CARD";
	public static final String MEAL_CARD = "MEAL_CARD";
	public static final String GIFT_CARD = "GIFT_CARD";
	
	// Flag
	public static final String MASTER_CARD = "MASTER_CARD";
	public static final String VISA = "VISA";
	public static final String ELO = "ELO";
	
}
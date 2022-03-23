package com.neptum.BankAccountApi.DTO.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.neptum.BankAccountApi.constants.Constants;
import com.neptum.BankAccountApi.customValidation.FlagConstraint;
import com.neptum.BankAccountApi.customValidation.LimitBalanceConstraint;

public class CardRequest
{
	@NotNull(message = Constants.CARD_NAME_NULL_CONSTRAINT)
	@Size(max = 50, message = Constants.CARD_NAME_SIZE_CONSTRAINT)
	private String name;
	@FlagConstraint(acceptedValues= {
		Constants.MASTER_CARD, Constants.VISA, Constants.ELO})
	private String flag;
	@Valid
	private CardTypeRquest cardTypeRequest;
	@NotNull(message = Constants.CARD_NUMBER_NULL_CONSTRAINT)
	@Size(max = 20, message = Constants.CARD_NUMBER_SIZE_CONSTRAINT)
	private String number;
	@NotNull(message = Constants.CARD_DIGIT_CODE_NULL_CONSTRAINT)
	@Size(max = 5, message = Constants.CARD_DIGIT_SIZE_CONSTRAINT)
	private String digitCode;
	@LimitBalanceConstraint
	private Double limitBalance;
	
	public String getName() { return name; }
	
	public String getFlag() { return flag; }
	
	public CardTypeRquest getCardTypeRequest() { return cardTypeRequest; }
	
	public String getNumber() { return number; }
	
	public String getDigitCode() { return digitCode; }
	
	public Double getLimitBalance() { return limitBalance; } 
}
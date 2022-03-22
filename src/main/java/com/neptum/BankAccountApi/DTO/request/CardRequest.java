package com.neptum.BankAccountApi.DTO.request;

public class CardRequest
{
	private String name;
	private String flag;
	private CardTypeRquest cardTypeRequest;
	private String number;
	private String digitCode;
	private Double limitBalance;
	
	public String getName() { return name; }
	
	public String getFlag() { return flag; }
	
	public CardTypeRquest getCardTypeRequest() { return cardTypeRequest; }
	
	public String getNumber() { return number; }
	
	public String getDigitCode() { return digitCode; }
	
	public Double getLimitBalance() { return limitBalance; } 
}
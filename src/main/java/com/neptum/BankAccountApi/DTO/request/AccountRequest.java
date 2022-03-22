package com.neptum.BankAccountApi.DTO.request;

import java.util.List;

public class AccountRequest
{
	private String nameOwner;
	private String agencyCode;
	private String accountCode;
	private String verificationDigital;
	private String registerId;
	private List<CardRequest> cardsRequest;
	
	public String getNameOwner() { return nameOwner; }
	
	public String getAgencyCode() { return agencyCode; }
	
	public String getAccountCode() { return accountCode; }
	
	public String getVerificationDigital() { return verificationDigital; }

	public String getRegisterId() { return registerId; }
	
	public List<CardRequest> getCardsRequest() { return cardsRequest; }
}
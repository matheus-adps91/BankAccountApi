package com.neptum.BankAccountApi.DTO.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.neptum.BankAccountApi.constants.Constants;

public class AccountRequest
{
	@NotNull(message = Constants.NAME_OWNER_NULL_CONSTRAINT)
	@Size(max = 50, message = Constants.NAME_OWNER_SIZE_CONSTRAINT)
	private String nameOwner;
	@NotNull(message = Constants.AGENCY_CODE_NULL_CONSTRAINT)
	@Size(max = 4, message = Constants.AGENCY_CODE_SIZE_CONSTRAINT)
	private String agencyCode;
	@NotNull(message = Constants.ACCOUNT_CODE_NULL_CONSTRAINT)
	@Size(max = 8, message = Constants.ACCOUNT_CODE_SIZE_CONSTRAINT)
	private String accountCode;
	@NotNull(message = Constants.VERIFICATION_DIGITAL_NULL_CONSTRAINT)
	@Size(max = 1, message = Constants.VERIFICATION_DIGITAL_SIZE_CONSTRAINT)
	private String verificationDigital;
	@NotNull(message = Constants.REGISTER_ID_NULL_CONSTRAINT)
	@Size(max = 20, message = Constants.REGISTER_ID_SIZE_CONSTRAINT)
	private String registerId;
	@JsonProperty(value = "cards")
	@Valid
	private List<CardRequest> cardsRequest;
	
	public AccountRequest() { }

	public AccountRequest(
		@NotNull(message = "Name owner is mandatory") @Size(max = 50, message = "Name owner max character size 50") String nameOwner,
		@NotNull(message = "Agency code is mandatory") @Size(max = 4, message = "Agency code max character size 4") String agencyCode,
		@NotNull(message = "Account code is mandatory") @Size(max = 8, message = "Account code max character size 8") String accountCode,
		@NotNull(message = "Verification Digital is mandatory") @Size(max = 1, message = "Verification digital owner max character size 1") String verificationDigital,
		@NotNull(message = "Register id is mandatory") @Size(max = 20, message = "Register id max character size 20") String registerId,
		@Valid List<CardRequest> cardsRequest) 
	{
		this.nameOwner = nameOwner;
		this.agencyCode = agencyCode;
		this.accountCode = accountCode;
		this.verificationDigital = verificationDigital;
		this.registerId = registerId;
		this.cardsRequest = cardsRequest;
	}

	public String getNameOwner() { return nameOwner; }
	
	public String getAgencyCode() { return agencyCode; }
	
	public String getAccountCode() { return accountCode; }
	
	public String getVerificationDigital() { return verificationDigital; }

	public String getRegisterId() { return registerId; }
	
	public List<CardRequest> getCardsRequest() { return cardsRequest; }
}
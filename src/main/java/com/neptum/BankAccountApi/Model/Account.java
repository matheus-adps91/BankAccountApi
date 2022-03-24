package com.neptum.BankAccountApi.Model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.DTO.request.CardRequest;

@Entity
@Table(name = "tb_account")
public class Account
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nameOwner;
	private String agencyCode;
	private String accountCode;
	@Column(name = "digit_verification")
	private String verificationDigital;
	@Column( name = "register_id")
	private String personalRegister;
	@OneToMany(targetEntity = Card.class, cascade = CascadeType.ALL, orphanRemoval = true )
	@JoinColumn(name = "account_id")
	private List<Card> cards;
	
	public Account() { }

	private Account(
		final String nameOwner, 
		final String agencyCode, 
		final String accountCode, 
		final String verificationDigital,
		final String registerId,
		final List<CardRequest> cardsRequest) 
	{
		this.nameOwner = nameOwner;
		this.agencyCode = agencyCode;
		this.accountCode = accountCode;
		this.verificationDigital = verificationDigital;
		this.personalRegister = registerId;
		this.cards = cardsRequest.stream().map( cardRequest -> new Card(cardRequest) ).collect(Collectors.toList());
	}

	public static Account getAccountInstance(
		final AccountRequest accountRequest)
	{
		return new Account(
			accountRequest.getNameOwner(), 
			accountRequest.getAgencyCode(), 
			accountRequest.getAccountCode(), 
			accountRequest.getVerificationDigital(),
			accountRequest.getRegisterId(),
			accountRequest.getCardsRequest());
	}		

	public Integer getId() { return id; }

	public String getNameOwner() { return nameOwner; }

	public String getAgencyCode() { return agencyCode; }

	public String getAccountCode() { return accountCode; }

	public String getVerificationDigital() { return verificationDigital; }
	
	public String getRegisterId() { return personalRegister; }

	public List<Card> getCards() { return cards; }
	
	public void setId(Integer id) { this.id = id; }
}
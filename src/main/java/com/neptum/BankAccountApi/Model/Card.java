package com.neptum.BankAccountApi.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.neptum.BankAccountApi.DTO.request.CardRequest;

@Entity
@Table(name="tb_card")
public class Card 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Enumerated(EnumType.STRING)
	private Flag flag;
	@ManyToOne(targetEntity = CardType.class)
	@JoinColumn
	private CardType cardType;
	private String number;
	private String digitCode;
	private Double limitBalance;
	
	public Card() { }
	
	public Card(
		final CardRequest cardRequest) 
	{
		this.name = cardRequest.getName();
		this.flag = Flag.valueOf(cardRequest.getFlag());
		this.cardType = new CardType(cardRequest.getCardTypeRequest().getCardType());
		this.number = cardRequest.getNumber();
		this.digitCode = cardRequest.getDigitCode();
		this.limitBalance = cardRequest.getLimitBalance();
	}
	
	public Card(
		final Integer id,
		final String name, 
		final Flag flag, 
		final CardType cardType, 
		final String number, 
		final String digitCode, 
		final Double limitBalance) 
	{
		this.id = id;
		this.name = name;
		this.flag = flag;
		this.cardType = cardType;
		this.number = number;
		this.digitCode = digitCode;
		this.limitBalance = limitBalance;
	}
	
	public Integer getId() { return id; }
	
	public String getName() { return name; }
	
	public Flag getFlag() { return flag; }
	
	public CardType getCardType() { return cardType; }
	
	public String getNumber() { return number; }
	
	public String getDigitCode() { return digitCode; }
	
	public Double getLimitBalance() { return limitBalance; }
	
}
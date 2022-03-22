package com.neptum.BankAccountApi.Model;

public enum Flag
{
	MASTERCARD("MASTERCARD"),
	VISA("VISA"),
	ELO("ELO");
	
	private final String flag;

	Flag(
		final String flag) 
	{
		this.flag = flag;
	}

	public String getFlag() { return flag; }
}
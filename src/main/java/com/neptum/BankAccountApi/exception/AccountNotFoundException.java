package com.neptum.BankAccountApi.exception;

public class AccountNotFoundException 
	extends 
		RuntimeException 
{
	private static final long serialVersionUID = 3433554057598833217L;
	
	public AccountNotFoundException(
		final String msg) 
	{
		super(msg);
	}
}
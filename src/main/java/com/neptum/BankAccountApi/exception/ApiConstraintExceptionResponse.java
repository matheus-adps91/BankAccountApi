package com.neptum.BankAccountApi.exception;

import java.util.Date;

public class ApiConstraintExceptionResponse 
{
	private Date date;
	private String field;
	private String msg;
	
	public ApiConstraintExceptionResponse(
		final Date date, 
		final String field, 
		final String msg) 
	{
		this.date = date;
		this.field = field;
		this.msg = msg;
	}

	public Date getDate() { return date; }
	
	public String getField() { return field; }

	public String getMsg() { return msg; }
}
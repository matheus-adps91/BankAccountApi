package com.neptum.BankAccountApi.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiExceptionResponse
{
	private Date date;
	private String msg;
	private HttpStatus httpStatus;
	
	public ApiExceptionResponse(
		final Date date, 
		final String msg, 
		final HttpStatus httpStatus) 
	{
		this.date = date;
		this.msg = msg;
		this.httpStatus = httpStatus;
	}

	public Date getDate() { return date; }

	public String getMsg() { return msg; }

	public HttpStatus getHttpStatus() { return httpStatus; }	
}
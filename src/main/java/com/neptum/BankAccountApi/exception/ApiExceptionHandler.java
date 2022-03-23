package com.neptum.BankAccountApi.exception;

import java.time.Instant;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler
{

	@ExceptionHandler(value = {AccountNotFoundException.class})
	public ResponseEntity<ApiExceptionResponse> handleAccountNotFoundException(
		final AccountNotFoundException ex) 
	{
		final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		final ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
				Date.from(Instant.now()),ex.getMessage(), badRequest);
		return new ResponseEntity<>(apiExceptionResponse, badRequest);
	}
}

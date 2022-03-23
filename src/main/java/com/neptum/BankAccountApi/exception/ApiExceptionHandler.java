package com.neptum.BankAccountApi.exception;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
			getNowDateTime(),ex.getMessage(), badRequest);
		return new ResponseEntity<>(apiExceptionResponse, badRequest);
	}

	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<List<ApiConstraintExceptionResponse>> handleInvalidMethodArgument(
		final MethodArgumentNotValidException ex)
	{
		final List<ObjectError> errors = ex.getBindingResult().getAllErrors();
		final List<ApiConstraintExceptionResponse> constraintsErrors = errors.stream()
			.map(error -> new ApiConstraintExceptionResponse(
					getNowDateTime(), ((FieldError) error).getField(), error.getDefaultMessage()))
			.collect(Collectors.toList());
		return new ResponseEntity<>(constraintsErrors, HttpStatus.BAD_REQUEST);
	}
	
	private Date getNowDateTime() {
		return Date.from(Instant.now());
	}
}

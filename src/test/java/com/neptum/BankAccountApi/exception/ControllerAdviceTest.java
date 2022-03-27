package com.neptum.BankAccountApi.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.neptum.BankAccountApi.constants.Constants;

@DisplayName("Exception handler test")
public class ControllerAdviceTest
{
	@InjectMocks
	private ApiExceptionHandler apiExceptionHandler;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@DisplayName("Should return bad request when an id is invalid")
	@Test
	void shouldThrowAccountNoFoundExceptionWhenIdIsInvalid() {
		ResponseEntity<ApiExceptionResponse> apiExceptionResponse = apiExceptionHandler.handleAccountNotFoundException(
			new AccountNotFoundException(Constants.ACCOUNT_NOT_FOUND_EXCEPTION));
		
		Assertions.assertNotNull(apiExceptionResponse);
		Assertions.assertNotNull(apiExceptionResponse.getBody());
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, apiExceptionResponse.getStatusCode());
		Assertions.assertEquals(Constants.ACCOUNT_NOT_FOUND_EXCEPTION, apiExceptionResponse.getBody().getMsg());
	}
}
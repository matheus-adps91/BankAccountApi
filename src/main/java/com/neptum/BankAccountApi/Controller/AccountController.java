package com.neptum.BankAccountApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neptum.BankAccountApi.DTO.request.AccountRequest;
import com.neptum.BankAccountApi.Model.Account;
import com.neptum.BankAccountApi.Service.AccountService;

@RestController
@RequestMapping("/api/v1/banking")
public class AccountController 
{
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/create")
	public ResponseEntity<Account> createAccount(
		final @RequestBody AccountRequest accountRequest) 
	{
		Account createdAccount = accountService.createAccount(accountRequest);
		return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
	}
}
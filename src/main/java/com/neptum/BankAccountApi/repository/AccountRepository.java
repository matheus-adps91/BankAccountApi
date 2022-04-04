package com.neptum.BankAccountApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptum.BankAccountApi.model.Account;

public interface AccountRepository 
	extends 
		JpaRepository<Account, Integer>
{ }
package com.neptum.BankAccountApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptum.BankAccountApi.Model.Account;

public interface AccountRepository 
	extends 
		JpaRepository<Account, Integer>
{ }
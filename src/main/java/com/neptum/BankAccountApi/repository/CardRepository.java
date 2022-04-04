package com.neptum.BankAccountApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptum.BankAccountApi.model.Card;

public interface CardRepository 
	extends 
		JpaRepository<Card, Integer> 
{ }
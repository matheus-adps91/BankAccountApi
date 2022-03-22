package com.neptum.BankAccountApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptum.BankAccountApi.Model.Card;

public interface CardRepository 
	extends 
		JpaRepository<Card, Integer> 
{ }
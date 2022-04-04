package com.neptum.BankAccountApi.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptum.BankAccountApi.model.CardType;
import com.neptum.BankAccountApi.model.Type;

public interface CardTypeRepository 
	extends 
		JpaRepository<CardType, Integer> 
{ 
	Set<CardType> findAllByTypeIn(Set<Type> types);
}
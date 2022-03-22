package com.neptum.BankAccountApi.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptum.BankAccountApi.Model.CardType;
import com.neptum.BankAccountApi.Model.Type;

public interface CardTypeRepository 
	extends 
		JpaRepository<CardType, Integer> 
{ 
	Set<CardType> findAllByTypeIn(Set<Type> types);
}
package com.neptum.BankAccountApi.customValidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardTypeValidator 
	implements 
		ConstraintValidator<CardTypeConstraint, String> 
{
	private List<String> acceptedValues;
    
	@Override
    public void initialize(
		final CardTypeConstraint targetValidator) 
	{
		this.acceptedValues = List.of(targetValidator.acceptedValues());
    }
	
    @Override
	public boolean isValid(
		final String sTypeCard, 
		final ConstraintValidatorContext context) 
    {
    	if (sTypeCard == null || sTypeCard.isBlank()) {
    		return false;
    	}
		return acceptedValues.contains(sTypeCard.toUpperCase());
	}
}
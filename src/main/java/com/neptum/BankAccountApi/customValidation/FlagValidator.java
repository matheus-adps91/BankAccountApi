package com.neptum.BankAccountApi.customValidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlagValidator 
	implements 
		ConstraintValidator<FlagConstraint, String> 
{
	private List<String> acceptedValues;
	
	@Override
	public void initialize(
		final FlagConstraint constraintAnnotation) 
	{
		this.acceptedValues = List.of(constraintAnnotation.acceptedValues());
	}
	
	@Override
	public boolean isValid(
		final String sFlag, 
		final ConstraintValidatorContext context) 
	{
		if (sFlag == null || sFlag.isBlank()) {
			return false;
		}
		return acceptedValues.contains(sFlag.toUpperCase());
	}

}

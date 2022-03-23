package com.neptum.BankAccountApi.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LimitBalanceValidator 
	implements 
		ConstraintValidator<LimitBalanceConstraint, Double> 
{
	@Override
	public boolean isValid(
		final Double value, 
		final ConstraintValidatorContext context) 
	{
		return value != null && !value.isNaN() && value > 0 && value.toString().length() <= 20;
	}
}
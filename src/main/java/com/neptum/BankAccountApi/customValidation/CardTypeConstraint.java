package com.neptum.BankAccountApi.customValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CardTypeValidator.class})
@Documented
public @interface CardTypeConstraint
{
	String[] acceptedValues();

	String message() default "Only values able: DEBIT_CARD, CREDIT_CARD, MEAL_CARD, GIFT_CARD";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
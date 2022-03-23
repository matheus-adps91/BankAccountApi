package com.neptum.BankAccountApi.customValidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FlagValidator.class})
public @interface FlagConstraint
{
	String[] acceptedValues();
	
	String message() default "Only values able: MASTER_CARD, VISA, ELO";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
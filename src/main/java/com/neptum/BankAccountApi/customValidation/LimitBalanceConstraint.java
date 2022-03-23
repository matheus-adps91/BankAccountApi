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
@Constraint(validatedBy = {LimitBalanceValidator.class})
@Documented
public @interface LimitBalanceConstraint 
{
    String message() default "Limit balance must be filled, greater than 0 and max number size 20";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
package com.luanoliveira.desafio.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = BankSlipInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface BankSlipInsert {
	String message() default "Field of the provided bankslip was with invalid values";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

package ru.testtask.walletapp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OperationTypeValidator.class)
public @interface ValidOperationType {

    String message() default "Операция должна быть указана как 'DEPOSIT' или 'WITHDRAW'";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
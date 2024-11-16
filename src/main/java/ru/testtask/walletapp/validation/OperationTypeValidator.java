package ru.testtask.walletapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.testtask.walletapp.model.OperationType;

// Валидатор для OperationType
public class OperationTypeValidator implements ConstraintValidator<ValidOperationType, OperationType> {

    @Override
    public void initialize(ValidOperationType constraintAnnotation) {
    }

    @Override
    public boolean isValid(OperationType value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return value == OperationType.DEPOSIT || value == OperationType.WITHDRAW;
    }
}
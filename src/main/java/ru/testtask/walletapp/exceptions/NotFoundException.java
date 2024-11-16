package ru.testtask.walletapp.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    UUID fieldValue;

    public NotFoundException(String resourceName, String fieldName, UUID fieldValue) {
        super(String.format("%s с %s: %s не найден!", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
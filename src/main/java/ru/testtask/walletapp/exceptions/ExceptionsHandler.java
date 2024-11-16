package ru.testtask.walletapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.testtask.walletapp.dto.ErrorResponse;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> insufficientFundsExceptionHandler(InsufficientFundsException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidOperationTypeException.class)
    public ResponseEntity<String> invalidOperationTypeExceptionHandler(InvalidOperationTypeException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        // ошибка связана с UUID
        if (ex.getMessage().contains("UUID")) {
            return new ResponseEntity<>(
                    new ErrorResponse("Неправильный формат ID. UUID должен быть в формате 'xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx'"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(new ErrorResponse("Неверный формат данных в запросе"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessages = new StringBuilder("Ошибка валидации: ");

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errorMessages.append("Поле '").append(error.getField())
                    .append("' - ").append(error.getDefaultMessage()).append("; ");
        }

        return new ResponseEntity<>(new ErrorResponse(errorMessages.toString()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Произошла ошибка на сервере. Попробуйте позже.");
        if (ex instanceof NullPointerException) {
            errorResponse.setMessage("Обнаружена ошибка в обработке данных.");
        } else if (ex instanceof IllegalArgumentException) {
            errorResponse.setMessage("Некорректный запрос.");
        }
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


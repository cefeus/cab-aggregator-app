package com.modsen.driverservice.controller;

import com.modsen.driverservice.dto.error.ErrorResponse;
import com.modsen.driverservice.exceptions.EmailAlreadyExistsException;
import com.modsen.driverservice.exceptions.NoDriverAvailableException;
import com.modsen.driverservice.exceptions.NumberAlreadyExistsException;
import com.modsen.driverservice.exceptions.PhoneAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            EntityNotFoundException.class,
            NoDriverAvailableException.class
    } )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        val exceptionId = UUID.randomUUID().toString();
        val message = ex.getMessage();

        return ErrorResponse.builder()
                .id(exceptionId)
                .message(message)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({
            PhoneAlreadyExistsException.class,
            NumberAlreadyExistsException.class,
            EmailAlreadyExistsException.class,
            IllegalArgumentException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(PhoneAlreadyExistsException ex) {
        val exceptionId = UUID.randomUUID().toString();
        val message = ex.getMessage();

        return ErrorResponse.builder()
                .id(exceptionId)
                .message(message)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        val exceptionId = UUID.randomUUID().toString();
        val message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" | "));

        return ErrorResponse.builder()
                .id(exceptionId)
                .message(message)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .build();
    }
}

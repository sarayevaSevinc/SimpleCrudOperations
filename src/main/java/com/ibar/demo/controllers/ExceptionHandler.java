package com.ibar.demo.controllers;

import com.ibar.demo.annotation.PinConstraint;
import com.ibar.demo.annotation.PinValidator;
import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.ExceptionEntity;
import com.ibar.demo.exceptions.PhoneNumberWithIdNotFound;
import com.ibar.demo.utilities.ErrorMapper;
import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AccountNotFoundException.class)
    public ExceptionEntity handleAccountNotFoundException() {
        return ExceptionEntity.builder()
                .code(404)
                .description(ErrorMapper.getUserNotFoundByIdError())
                .build();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(DateTimeParseException.class)
    public ExceptionEntity handleDateTimeParseException() {
        return ExceptionEntity.builder()
                .code(404)
                .description("Incorrect birth date")
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PhoneNumberWithIdNotFound.class)
    public ExceptionEntity handlePhoneNumberWithIdNotFoundException() {
        return ExceptionEntity.builder()
                .code(404)
                .description(ErrorMapper.getPhoneNumberNotFoundWithIDError())
                .build();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler()
    public ExceptionEntity exception() {
        return ExceptionEntity.builder()
                .code(404)
                .description(ErrorMapper.getPhoneNumberNotFoundWithIDError())
                .build();
    }

}
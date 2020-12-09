package com.ibar.demo.controllers;

import com.ibar.demo.exceptions.AccountNotFoundException;
import com.ibar.demo.exceptions.ExceptionEntity;
import com.ibar.demo.exceptions.PhoneNumberWithIdNotFound;
import com.ibar.demo.utilities.ErrorMapper;
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

    @org.springframework.web.bind.annotation.ExceptionHandler(PhoneNumberWithIdNotFound.class)
    public ExceptionEntity handlePhoneNumberWithIdNotFoundException() {
        return ExceptionEntity.builder()
                .code(404)
                .description(ErrorMapper.getPhoneNumberNotFoundWithIDError())
                .build();
    }

}
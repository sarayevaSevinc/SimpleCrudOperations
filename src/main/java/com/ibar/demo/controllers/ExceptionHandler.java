package com.ibar.demo.controllers;

import com.ibar.demo.exceptions.ExceptionEntity;
import com.ibar.demo.utilities.ErrorMapper;
import java.time.format.DateTimeParseException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(DateTimeParseException.class)
    public ExceptionEntity handleDateTimeParseException() {
        return ExceptionEntity.builder()
                .code(404)
                .description("Incorrect birth date")
                .build();
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
    public ExceptionEntity exception(ConstraintViolationException ex, WebRequest request) {
        return ExceptionEntity.builder()
                .code(404)
                .description(ex.getConstraintViolations().stream().findAny().get().getMessage())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionEntity notValidexception(MethodArgumentNotValidException ex,
                                             WebRequest request) {
        return ExceptionEntity.builder()
                .code(404)
                .description(ex.getBindingResult().getAllErrors().stream().findAny().get().getDefaultMessage())
                .build();
    }
}

package com.ibar.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains("@")
                && (value.split("@")[1].contains("gmail.com")
                || value.split("@")[1].contains("mail.ru")
        );
    }
}

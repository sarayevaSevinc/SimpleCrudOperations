package com.ibar.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PinValidator implements ConstraintValidator<PinConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() == 7 && value.matches("[A-Za-z0-9]+");
    }
}

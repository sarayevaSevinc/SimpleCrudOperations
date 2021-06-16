package com.ibar.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<GenderConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.toLowerCase().equals("w")
                || value.toLowerCase().equals("m")
                || value.toLowerCase().equals("woman")
                || value.toLowerCase().equals("man");

    }
}

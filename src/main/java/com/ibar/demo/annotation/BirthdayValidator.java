package com.ibar.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BirthdayValidator implements ConstraintValidator<BirthdayConstraint, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("[0-9-]+") && value.length()==10
                && (value.split("-").length==3
                && value.split("-")[0].length()==4
                && value.split("-")[1].length()==2
                && value.split("-")[2].length()==2);

    }
}

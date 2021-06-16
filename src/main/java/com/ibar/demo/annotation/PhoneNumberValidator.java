package com.ibar.demo.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberConstraint, String> {
    @Override
    public void initialize(PhoneNumberConstraint phoneNumber) {
    }

    @Override
    public boolean isValid(String phoneNumber,
                           ConstraintValidatorContext cxt) {
        return phoneNumber != null && (phoneNumber.matches("[0-9+]+"))
                && (phoneNumber.length() == 13)
                && (phoneNumber.startsWith("+99477")
                || phoneNumber.startsWith("+99450")
                || phoneNumber.startsWith("+99451")
                || phoneNumber.startsWith("+99470")
                || phoneNumber.startsWith("+99455")
                || phoneNumber.startsWith("+994599"));


    }
}

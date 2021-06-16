package com.ibar.demo.annotation;

import com.ibar.demo.repositories.UserRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsValidValidator implements ConstraintValidator<IsValidConstraint, String> {
    @Autowired
    UserRepository userRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return  !userRepository.getUserByPin(value).isPresent();
    }
}

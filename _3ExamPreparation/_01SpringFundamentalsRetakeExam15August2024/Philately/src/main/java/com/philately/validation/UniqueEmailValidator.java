package com.philately.validation;


import jakarta.validation.ConstraintValidatorContext;
import com.philately.service.UserService;
import com.philately.validation.annotation.UniqueEmail;

import jakarta.validation.ConstraintValidator;
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return this.userService.getUserByEmail(value).isEmpty();
    }


}
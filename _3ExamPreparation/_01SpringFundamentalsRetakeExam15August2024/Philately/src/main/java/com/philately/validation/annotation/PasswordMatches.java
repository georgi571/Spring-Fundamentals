package com.philately.validation.annotation;

import com.philately.validation.PasswordMatchesValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "{password.didnt.match}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
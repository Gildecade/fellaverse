package com.fellaverse.backend.annotation;

import com.fellaverse.backend.validator.UserValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = UserValidation.UniqueUserValidator.class)
public @interface UniqueUser {
    String message() default "Username and email are not allowed to be duplicated with existing users";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

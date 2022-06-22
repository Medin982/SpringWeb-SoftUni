package com.example.battleship.config.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IsMatchedValidation.class)
public @interface IsMatched {

    String first();

    String second();

    String message() default "Password must be match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

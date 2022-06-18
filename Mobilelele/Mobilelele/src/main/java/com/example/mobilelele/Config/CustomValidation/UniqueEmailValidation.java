package com.example.mobilelele.Config.CustomValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = UniqueEmailValidationImpl.class)
public @interface UniqueEmailValidation {

    String message() default "Email is already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

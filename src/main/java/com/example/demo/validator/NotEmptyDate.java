package com.example.demo.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DateConstraintValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyDate {

    String message() default "com.example.demo.validator";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

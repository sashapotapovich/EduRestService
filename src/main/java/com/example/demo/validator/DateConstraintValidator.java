package com.example.demo.validator;

import java.time.LocalDateTime;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<NotEmptyDate, LocalDateTime> {
   public void initialize(NotEmptyDate constraint) {
   }

   public boolean isValid(LocalDateTime obj, ConstraintValidatorContext context) {
      return obj != null;
   }
}

package com.mako.accountservice.annotation.constraint;

import com.mako.accountservice.annotation.WithinTimeRange;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class WithinTimeRangeValidator implements ConstraintValidator<WithinTimeRange, LocalDateTime> {

    private int maxMinutes;

    @Override
    public void initialize(WithinTimeRange constraintAnnotation) {
        maxMinutes = constraintAnnotation.maxMinutes();
    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        return value.isAfter(now.minus(maxMinutes, ChronoUnit.MINUTES));
    }
}

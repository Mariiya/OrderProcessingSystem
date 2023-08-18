package com.mako.accountservice.annotation;

import com.mako.accountservice.annotation.constraint.WithinTimeRangeValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WithinTimeRangeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WithinTimeRange {
    String message() default "Date must be within the specified time range";
    int maxMinutes() default 2;
}

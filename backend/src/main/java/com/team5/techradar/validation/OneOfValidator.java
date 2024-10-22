package com.team5.techradar.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class OneOfValidator implements ConstraintValidator<OneOf, Integer> {

    private int[] values;

    @Override
    public void initialize(OneOf constraintAnnotation) {
        this.values = constraintAnnotation.values();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return integer != null && (values.length == 0
                || Arrays.stream(values).anyMatch(value -> value == integer));
    }
}

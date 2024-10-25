package com.team5.techradar.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfValidator.class)
public @interface OneOf {

    String message() default "{field.invalid}";

    int[] values() default {};

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

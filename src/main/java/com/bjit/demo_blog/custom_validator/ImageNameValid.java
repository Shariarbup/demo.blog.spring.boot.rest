package com.bjit.demo_blog.custom_validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {
    // error message
    String message() default "Image Name Not Valid";

    // represent group of constraint
    Class<?>[] groups() default {};

    // additional information of annotation
    Class<? extends Payload>[] payload() default {};
}

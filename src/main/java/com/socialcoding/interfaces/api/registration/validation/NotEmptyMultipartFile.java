package com.socialcoding.interfaces.api.registration.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = MultipartFileConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyMultipartFile {
    String message() default "{NotEmptyMultipartFile}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

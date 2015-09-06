package com.socialcoding.interfaces.api.registration.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFileConstraintValidator implements ConstraintValidator<NotEmptyMultipartFile, MultipartFile>{
    @Override
    public void initialize(NotEmptyMultipartFile constraintAnnotation) {
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null) {
            return false;
        }
        return !file.isEmpty();
    }
}

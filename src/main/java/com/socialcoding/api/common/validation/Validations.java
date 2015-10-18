package com.socialcoding.api.common.validation;

import org.springframework.web.multipart.MultipartFile;

public class Validations {
    public static void validateImageType(MultipartFile image) {
        if (image == null) {
            return;
        }

        if (!image.getContentType().startsWith("image")) {
            throw new RuntimeException("Not image file");
        }
    }
}

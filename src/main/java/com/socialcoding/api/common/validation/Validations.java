package com.socialcoding.api.common.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class Validations {
    public static void validateImage(MultipartFile image, boolean ignoreEmpty) {
        if (image == null || StringUtils.isEmpty(image.getOriginalFilename())) {
			if (ignoreEmpty) {
				return;
			} else {
				throw new RuntimeException("No image file attached");
			}
        }

        if (!image.getContentType().startsWith("image")) {
            throw new RuntimeException("Not image file");
        }
    }
}

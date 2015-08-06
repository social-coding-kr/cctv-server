package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import static com.socialcoding.Constants.DEFAULT_COMMENT_PAGE_SIZE;

public class Request {
    @Getter
    @Setter
    public static class ReliabilitySelectDto {
        @NotBlank
        private String userId;
        @NotNull
        private Boolean reliable;
    }

    @Getter
    @Setter
    public static class CctvPositionDto {
        @NotNull
        private Long latitude;
        @NotNull
        private Long longitude;
    }

    @Getter
    @Setter
    public static class CommentWriteDto {
        @NotBlank
        private String userId;
        @NotBlank
        private String contents;

        private int size = DEFAULT_COMMENT_PAGE_SIZE;
    }

    @Getter
    @Setter
    public static class CommentLoadDto {
        @NotNull
        private Long fromCommentId;

        private int size = DEFAULT_COMMENT_PAGE_SIZE;
    }

    @Getter
    @Setter
    public static class CctvRegistrationDto {
        @NotNull
        private Long latitude;
        @NotNull
        private Long longitude;
        @NotNull
        private CctvPurpose purpose;
        @NotNull
        private MultipartFile cctvImage;
        @NotBlank
        private String userId;

        private MultipartFile noticeImage;

    }
}

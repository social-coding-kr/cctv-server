package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import com.socialcoding.domain.services.cctv.Position;
import com.socialcoding.interfaces.api.registration.validation.NotEmptyMultipartFile;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

import static com.socialcoding.Constants.DEFAULT_COMMENT_PAGE_SIZE;

public class Request {
    @Getter
    @Setter
    public static class MapPositionDto {
        @NotNull
        private Position northEast;
        @NotNull
        private Position southWest;
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
    public static class CommentDeletionDto {
        @NotBlank
        private String userId;
        @NotNull
        private Long commentId;
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
        @NotEmptyMultipartFile
        private MultipartFile cctvImage;
        @NotBlank
        private String userId;

        private MultipartFile noticeImage;
    }
}

package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

import static com.socialcoding.Constants.DEFAULT_COMMENT_PAGE_SIZE;

public class Request {
    @Getter
    @Setter
    public static class MapPositionDto {
        @NotNull
        private BigDecimal south;
        @NotNull
        private BigDecimal west;
        @NotNull
        private BigDecimal north;
        @NotNull
        private BigDecimal east;
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
        private BigDecimal latitude;
        @NotNull
        private BigDecimal longitude;
        @NotNull
        private CctvPurpose purpose;
        @NotBlank
        private String createdBy;
    }
}

package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import com.socialcoding.domain.services.cctv.CctvSource;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Response {
    public enum ResponseStatus {
        SUCCESS,
        FAILURE
    }

    @Getter
    @Setter
    public static class MapCctvDto {
        private long cctvId;
        private BigDecimal latitude;
        private BigDecimal longitude;
        private CctvPurpose purpose;
    }

    @Getter
    @Setter
    public static class CctvDetailDto {
        private long cctvId;
        private String address;
        private String cctvImage;
        private String noticeImage;
        private CctvPurpose purpose;
        private CctvSource source;
    }

    @Getter
    @Setter
    public static class CommentBundleDto {
        private long nextCommentId;
        private List<CommentDto> comments;
    }

    @Getter
    @Setter
    public static class CommentDto {
        private long commentId;
        private String userId;
        private String contents;
        private Date createdAt;
    }
}

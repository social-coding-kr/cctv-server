package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.CctvPurpose;
import com.socialcoding.domain.CctvSource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Response {
    public enum ResponseStatus {
        SUCCESS,
        FAILURE
    }

    @Getter
    @Setter
    public static class CctvValidationDto {
        private long cctvId;
        private String cctvImage;
    }

    @Getter
    @Setter
    public static class CctvOverviewDto {
        private long cctvId;
        private double latitude;
        private double longitude;
        private String color;
    }

    @Getter
    @Setter
    public static class CctvDetailDto {
        private long cctvId;
        private int correctPoint;
        private int incorrectPoint;
        private String address;
        private String cctvImage;
        private String noticeImage;
        private CctvPurpose purpose;
        private CctvSource source;
    }

    @Getter
    @Setter
    public static class CommentDto {
        private String userId;
        private String contents;
        private Date createdAt;
    }
}

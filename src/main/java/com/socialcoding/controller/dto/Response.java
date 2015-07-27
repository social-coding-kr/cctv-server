package com.socialcoding.controller.dto;

import com.socialcoding.service.CctvPurpose;
import com.socialcoding.service.CctvSource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Response {
    public enum ResponseCode {
        SUCCESS,
        FAILURE
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

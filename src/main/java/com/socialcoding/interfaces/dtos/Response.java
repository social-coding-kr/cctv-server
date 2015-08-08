package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.services.cctv.CctvPurpose;
import com.socialcoding.domain.services.cctv.CctvSource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

public class Response {
    public enum ResponseStatus {
        SUCCESS,
        FAILURE
    }

    @Getter
    @Setter
    public static class NearestCctvDto {
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
        private String address;
        private String cctvImage;
        private String noticeImage;
        private CctvPurpose purpose;
        private CctvSource source;
    }

    @Getter
    @Setter
    public static class CommentBundleDto {
        private long nextRequestCommentId;
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

	@Getter
	@Setter
	public static class TotalReliabilityDto {
		private long correctReliability;
		private long incorrectReliability;
	}

    @Getter
    @Setter
    public static class UserReliabilityDto {
        private long reliabilityId;
        private boolean reliable;
    }
}

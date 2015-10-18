package com.socialcoding.api.comment.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDto {
    private long commentId;
    private String userId;
    private String contents;
    private Date createdAt;
}

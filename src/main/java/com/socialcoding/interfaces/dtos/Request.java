package com.socialcoding.interfaces.dtos;

import lombok.Getter;
import lombok.Setter;

import static com.socialcoding.Constants.DEFAULT_COMMENT_PAGE_SIZE;

public class Request {
    @Getter
    @Setter
    public static class CommentLoadDto {
        private Long fromCommentId;
        private int size = DEFAULT_COMMENT_PAGE_SIZE;
    }
}

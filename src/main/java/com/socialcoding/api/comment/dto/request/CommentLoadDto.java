package com.socialcoding.api.comment.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentLoadDto {
    @NotNull
    private Long fromCommentId;

    private int size = 5;
}

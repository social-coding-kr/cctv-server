package com.socialcoding.api.comment.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class CommentWriteDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String contents;

    private int size = 5;
}

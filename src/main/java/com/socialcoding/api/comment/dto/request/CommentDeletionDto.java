package com.socialcoding.api.comment.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CommentDeletionDto {
    @NotBlank
    private String userId;
    @NotNull
    private Long commentId;
}

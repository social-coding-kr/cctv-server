package com.socialcoding.api.comment.assembler;

import com.socialcoding.api.comment.dto.request.CommentWriteDto;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.base.assembler.AbstractAssembler;
import org.springframework.stereotype.Component;

@Component
public class CommentAssembler extends AbstractAssembler<CommentWriteDto, Comment> {
    @Override
    protected Comment doAssemble(CommentWriteDto dto) {
        Comment comment = new Comment();
        comment.setUserId(dto.getUserId());
        comment.setContents(dto.getContents());
        return comment;
    }
}

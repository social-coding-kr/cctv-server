package com.socialcoding.api.comment.controller;

import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.comment.dto.request.CommentDeletionDto;
import com.socialcoding.api.comment.dto.request.CommentLoadDto;
import com.socialcoding.api.comment.dto.request.CommentWriteDto;
import com.socialcoding.api.comment.dto.response.CommentBundleDto;
import com.socialcoding.api.comment.dto.response.CommentDto;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.cctv.service.CctvService;
import com.socialcoding.api.comment.service.CommentService;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.assemble.ObjectMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CommentController {
    private static final Type TYPE_COMMENT_DTO = new TypeToken<List<CommentDto>>() {}.getType();

    @Autowired
    private CctvService cctvService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/cctv/{cctvId}/comments", method = RequestMethod.GET)
    public Map<String, Object> getComments(@PathVariable Long cctvId, @Valid CommentLoadDto commentLoadDto) {
        List<Comment> comments = commentService.getCommentsByCctvIdWithPagination(cctvId, commentLoadDto.getFromCommentId(), commentLoadDto.getSize());
        CommentBundleDto commentBundleDto = new CommentBundleDto();
        commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
        commentBundleDto.setComments(ObjectMapper.map(comments, TYPE_COMMENT_DTO));

        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
                put("comments", commentBundleDto);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/comment", method = RequestMethod.POST)
    public Map<String, Object> writeComment(@PathVariable Long cctvId, @RequestBody @Valid CommentWriteDto commentWriteDto) {
        Cctv cctv = cctvService.getCctvById(cctvId);
        Comment comment = ObjectMapper.map(commentWriteDto, cctv);
        commentService.writeComment(comment);

        List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId, commentWriteDto.getSize());
        CommentBundleDto commentBundleDto = new CommentBundleDto();
        commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
        commentBundleDto.setComments(ObjectMapper.map(comments, TYPE_COMMENT_DTO));

        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
                put("comments", commentBundleDto);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/comment", method = RequestMethod.DELETE)
    public Map<String, Object> deleteComment(@PathVariable Long cctvId, @RequestBody @Valid CommentDeletionDto commentDeletionDto) {
        //TODO validation
        //TODO test
        commentService.deleteComment(commentDeletionDto.getCommentId());
        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
            }
        };
    }
}

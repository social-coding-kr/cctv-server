package com.socialcoding.interfaces.api.detail;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.domain.services.comment.CommentService;
import com.socialcoding.interfaces.dtos.ObjectMapper;
import com.socialcoding.interfaces.dtos.Request.CommentLoadDto;
import com.socialcoding.interfaces.dtos.Request.CommentWriteDto;
import com.socialcoding.interfaces.dtos.Response.CommentBundleDto;
import com.socialcoding.interfaces.dtos.Response.CommentDto;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

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
                put("status", SUCCESS);
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
                put("status", SUCCESS);
                put("comments", commentBundleDto);
            }
        };
    }

}

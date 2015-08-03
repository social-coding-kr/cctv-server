package com.socialcoding.interfaces.api.detail;

import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.services.comment.CommentService;
import com.socialcoding.interfaces.dtos.Request;
import com.socialcoding.interfaces.dtos.Request.CommentLoadDto;
import com.socialcoding.interfaces.dtos.Response;
import com.socialcoding.interfaces.dtos.Response.CommentBundleDto;
import com.socialcoding.interfaces.dtos.Response.CommentDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@RestController
public class CommentController {
    private static final ModelMapper MAPPER = new ModelMapper();
    private static final Type TYPE_COMMENT_DTO = new TypeToken<List<CommentDto>>() {}.getType();

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/cctv/{cctvId}/comments", method = RequestMethod.GET)
    public Map<String, Object> getComments(@PathVariable Long cctvId, CommentLoadDto commentLoadDto) {
        List<Comment> comments = commentService.getCommentsByCctvIdWithPagination(cctvId, commentLoadDto.getFromCommentId(), commentLoadDto.getSize());
        CommentBundleDto commentBundleDto = new CommentBundleDto();
        commentBundleDto.setNextRequestCommentId(commentService.getNextRequestCommentId(comments));
        commentBundleDto.setComments(MAPPER.map(comments, TYPE_COMMENT_DTO));

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("comments", commentBundleDto);
            }
        };
    }

}

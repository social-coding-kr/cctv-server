package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.response.CctvDetailDto;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.comment.dto.response.CommentBundleDto;
import com.socialcoding.api.comment.dto.response.CommentDto;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.cctv.service.CctvService;
import com.socialcoding.api.comment.service.CommentService;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.assemble.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CctvDetailController {
	private static final Type TYPE_COMMENT_DTO = new TypeToken<List<CommentDto>>() {}.getType();

	@Autowired
	private CctvService cctvService;
	@Autowired
	private CommentService commentService;

    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public Map<String, Object> getCctv(@PathVariable Long cctvId) {
		Cctv cctv = cctvService.getCctvById(cctvId);
		CctvDetailDto cctvDetailDto = ObjectMapper.map(cctv, CctvDetailDto.class);

		List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
		CommentBundleDto commentBundleDto = new CommentBundleDto();
		commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
		commentBundleDto.setComments(ObjectMapper.map(comments, TYPE_COMMENT_DTO));

        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
                put("cctv", cctvDetailDto);
                put("comments", commentBundleDto);
            }
        };
    }
}

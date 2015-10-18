package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.response.CctvDetailDto;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.comment.dto.response.CommentBundleDto;
import com.socialcoding.api.comment.dto.response.CommentDto;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.cctv.service.CctvService;
import com.socialcoding.api.comment.service.CommentService;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.assembler.ObjectAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class CctvDetailController {
    @Autowired
    private ObjectAssembler assembler;
	@Autowired
	private CctvService cctvService;
	@Autowired
	private CommentService commentService;

    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public Map<String, Object> getCctv(@PathVariable Long cctvId) {
		Cctv cctv = cctvService.getCctvById(cctvId);
		CctvDetailDto cctvDetailDto = assembler.assemble(cctv, CctvDetailDto.class);

		List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
		CommentBundleDto commentBundleDto = new CommentBundleDto();
		commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
		commentBundleDto.setComments(assembler.assemble(comments, CommentDto.class));

        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
                put("cctv", cctvDetailDto);
                put("comments", commentBundleDto);
            }
        };
    }
}

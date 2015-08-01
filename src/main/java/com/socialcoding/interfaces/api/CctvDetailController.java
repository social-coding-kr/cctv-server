package com.socialcoding.interfaces.api;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.services.CctvService;
import com.socialcoding.domain.services.CommentService;
import com.socialcoding.interfaces.dtos.Response.CctvDetailDto;
import com.socialcoding.interfaces.dtos.Response.CommentDto;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
public class CctvDetailController {
	private static final ModelMapper MAPPER = new ModelMapper();
	private static final Type TYPE_COMMENT_DTO = new TypeToken<List<CommentDto>>() {}.getType();

	@Autowired
	private CctvService cctvService;
	@Autowired
	private CommentService commentService;

    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public Map<String, Object> getCctv(@PathVariable Long cctvId) {
		Cctv cctv = cctvService.getCctvDetailById(cctvId);

		CctvDetailDto cctvDetailDto = MAPPER.map(cctv, CctvDetailDto.class);
		List<CommentDto> commentDtos = MAPPER.map(cctv.getComments(), TYPE_COMMENT_DTO);

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctv", cctvDetailDto);
                put("comments", commentDtos);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/comments", method = RequestMethod.GET)
    public Map<String, Object> getComments(@PathVariable Long cctvId, Long page) {
		List<Comment> comments = commentService.getCommentsByCctvId(cctvId);
		List<CommentDto> commentDtos = MAPPER.map(comments, TYPE_COMMENT_DTO);

		return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("comments", commentDtos);
            }
        };
    }
}

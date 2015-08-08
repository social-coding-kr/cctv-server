package com.socialcoding.interfaces.api.detail;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.domain.services.comment.CommentService;
import com.socialcoding.domain.services.reliability.ReliabilityService;
import com.socialcoding.interfaces.dtos.ObjectMapper;
import com.socialcoding.interfaces.dtos.Response.CctvDetailDto;
import com.socialcoding.interfaces.dtos.Response.CommentBundleDto;
import com.socialcoding.interfaces.dtos.Response.CommentDto;
import com.socialcoding.interfaces.dtos.Response.TotalReliabilityDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@Slf4j
@RestController
public class CctvDetailController {
	private static final Type TYPE_COMMENT_DTO = new TypeToken<List<CommentDto>>() {}.getType();

	@Autowired
	private CctvService cctvService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReliabilityService reliabilityService;

    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public Map<String, Object> getCctv(@PathVariable Long cctvId) {
		Cctv cctv = cctvService.getCctvDetailById(cctvId);
		CctvDetailDto cctvDetailDto = ObjectMapper.map(cctv, CctvDetailDto.class);

		TotalReliabilityDto totalReliabilityDto = new TotalReliabilityDto();
		totalReliabilityDto.setCorrectReliability(reliabilityService.getCorrectReliability(cctv.getReliabilities()));
		totalReliabilityDto.setIncorrectReliability(reliabilityService.getIncorrectReliability(cctv.getReliabilities()));
        //TODO 현재 유저의 선택 정보 넘기기

		List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
		CommentBundleDto commentBundleDto = new CommentBundleDto();
		commentBundleDto.setNextRequestCommentId(commentService.getNextRequestCommentId(comments));
		commentBundleDto.setComments(ObjectMapper.map(comments, TYPE_COMMENT_DTO));

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctv", cctvDetailDto);
				put("totalReliability", totalReliabilityDto);
                put("comments", commentBundleDto);
            }
        };
    }
}

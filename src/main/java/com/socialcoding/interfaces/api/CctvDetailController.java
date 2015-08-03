package com.socialcoding.interfaces.api;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.domain.services.comment.CommentService;
import com.socialcoding.domain.services.reliability.ReliabilityService;
import com.socialcoding.domain.services.reliability.ReliablePoint;
import com.socialcoding.interfaces.dtos.Request;
import com.socialcoding.interfaces.dtos.Request.CommentLoadDto;
import com.socialcoding.interfaces.dtos.Response.CctvDetailDto;
import com.socialcoding.interfaces.dtos.Response.CommentBundleDto;
import com.socialcoding.interfaces.dtos.Response.CommentDto;
import com.socialcoding.interfaces.dtos.Response.ReliabilityDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
	private static final ModelMapper MAPPER = new ModelMapper();
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
		CctvDetailDto cctvDetailDto = MAPPER.map(cctv, CctvDetailDto.class);

		ReliabilityDto reliabilityDto = new ReliabilityDto();
		reliabilityDto.setCorrectPoint(reliabilityService.getCorrectPoint(cctv.getReliabilities()));
		reliabilityDto.setIncorrectPoint(reliabilityService.getIncorrectPoint(cctv.getReliabilities()));

		List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
		CommentBundleDto commentBundleDto = new CommentBundleDto();
		commentBundleDto.setNextRequestCommentId(commentService.getNextRequestCommentId(comments));
		commentBundleDto.setComments(MAPPER.map(comments, TYPE_COMMENT_DTO));

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctv", cctvDetailDto);
				put("reliability", reliabilityDto);
                put("comments", commentBundleDto);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/comments", method = RequestMethod.GET)
    public Map<String, Object> getComments(@PathVariable Long cctvId, CommentLoadDto commentLoadDto) {
		//TODO make request object
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

	@RequestMapping(value = "/cctv/{cctvId}/reliability")
	public Map<String, Object> getReliabilities(@PathVariable Long cctvId) {
		ReliablePoint reliablePoint = reliabilityService.getReliablePointByCctvId(cctvId);
		ReliabilityDto reliabilityDto = MAPPER.map(reliablePoint, ReliabilityDto.class);

		//TODO 현재 유저의 선택 정보 넘기기

		return new HashMap<String, Object>() {
			{
				put("status", SUCCESS);
				put("reliability", reliabilityDto);
			}
		};
	}
}

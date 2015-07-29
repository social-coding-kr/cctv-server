package com.socialcoding.controller.api;

import com.google.common.collect.Lists;
import com.socialcoding.service.CctvPurpose;
import com.socialcoding.service.CctvSource;
import com.socialcoding.controller.dto.Response.CctvDetailDto;
import com.socialcoding.controller.dto.Response.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.socialcoding.controller.dto.Response.ResponseStatus.SUCCESS;

@Slf4j
@RestController
public class CctvDetailController {
    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public Map<String, Object> getCctv(@PathVariable Long cctvId) {
        CctvDetailDto cctvDetailDto = new CctvDetailDto();
        cctvDetailDto.setCctvId(10L);
        cctvDetailDto.setCorrectPoint(100);
        cctvDetailDto.setIncorrectPoint(10);
        cctvDetailDto.setAddress("서울시 관악구 어디어디");
        cctvDetailDto.setCctvImage("https://pixabay.com/static/uploads/photo/2013/07/13/09/46/surveillance-155982_640.png");
        cctvDetailDto.setNoticeImage("http://privacy.jiran.com/wp-content/uploads/2012/04/cctv_00.jpg");
        cctvDetailDto.setPurpose(CctvPurpose.TRAFFIC);
        cctvDetailDto.setSource(CctvSource.BOROUGH);

        CommentDto unicornComment = new CommentDto();
        unicornComment.setUserId("Unicorn");
        unicornComment.setContents("good cctv");
        unicornComment.setCreatedAt(new Date(1438011411));

        CommentDto bansheeComment = new CommentDto();
        bansheeComment.setUserId("Banshee");
        bansheeComment.setContents("bad cctv");
        bansheeComment.setCreatedAt(new Date(1438011488));

        List<CommentDto> comments = Lists.newArrayList(unicornComment, bansheeComment);

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctv", cctvDetailDto);
                put("comments", comments);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/comments", method = RequestMethod.GET)
    public Map<String, Object> getComments(@PathVariable Long cctvId, Long page) {
        CommentDto phenexComment = new CommentDto();
        phenexComment.setUserId("Phenex");
        phenexComment.setContents("very good cctv");
        phenexComment.setCreatedAt(new Date(1438011784));

        CommentDto sinanjuComment = new CommentDto();
        sinanjuComment.setUserId("Sinanju");
        sinanjuComment.setContents("very bad cctv");
        sinanjuComment.setCreatedAt(new Date(1438011879));

        List<CommentDto> comments = Lists.newArrayList(phenexComment, sinanjuComment);

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("comments", comments);
            }
        };
    }
}

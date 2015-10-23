package com.socialcoding.api.cctv.service;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
import com.socialcoding.api.cctv.dto.request.MapPositionDto;
import com.socialcoding.api.cctv.dto.response.*;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.comment.dto.response.CommentBundleDto;
import com.socialcoding.api.comment.dto.response.CommentDto;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.comment.service.CommentService;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.assembler.ObjectAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
public class CctvFacadeService {
    @Autowired
    private ObjectAssembler assembler;
    @Autowired
    private CctvService cctvService;
    @Autowired
    private CommentService commentService;

    public CctvDetailResult getCctvDetail(Long cctvId) {
        Cctv cctv = cctvService.getCctvById(cctvId);
        CctvDto cctvDto = assembler.assemble(cctv, CctvDto.class);

        List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
        CommentBundleDto commentBundleDto = new CommentBundleDto();
        commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
        commentBundleDto.setComments(assembler.assemble(comments, CommentDto.class));

        CctvDetailResult cctvDetailResult = new CctvDetailResult();
        cctvDetailResult.setStatus(ResponseStatus.SUCCESS);
        cctvDetailResult.setCctv(cctvDto);
        cctvDetailResult.setComments(commentBundleDto);
        return cctvDetailResult;
    }

    public MapCctvResult listCctvBetween(MapPositionDto positions) {
        Position southWest = Position.of(positions.getSouth(), positions.getWest());
        Position northEast = Position.of(positions.getNorth(), positions.getEast());
        List<Cctv> cctvs = cctvService.listCctvBetween(southWest, northEast);
        List<CctvOverviewDto> cctvOverviewDtos = assembler.assemble(cctvs, CctvOverviewDto.class);

        MapCctvResult mapCctvResult = new MapCctvResult();
        mapCctvResult.setStatus(ResponseStatus.SUCCESS);
        mapCctvResult.setCctvs(cctvOverviewDtos);
        return mapCctvResult;
    }

    public CctvRegistrationResult registerPrivateCctv(CctvRegistrationForm form, MultipartFile cctvImage, MultipartFile noticeImage) {
		Cctv cctv = cctvService.registerPrivateCctv(form, cctvImage, noticeImage);

		CctvRegistrationResult result = new CctvRegistrationResult();
        result.setStatus(ResponseStatus.SUCCESS);
        result.setCctvId(cctv.getCctvId());
        return result;
    }
}

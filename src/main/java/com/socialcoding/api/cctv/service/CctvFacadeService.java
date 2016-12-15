package com.socialcoding.api.cctv.service;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
import com.socialcoding.api.cctv.dto.request.MapPositionForm;
import com.socialcoding.api.cctv.dto.response.*;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.cctv.model.PrivateCctv;
import com.socialcoding.api.cctv.model.PublicCctv;
import com.socialcoding.api.comment.service.CommentService;
import com.socialcoding.api.base.ResponseStatus;
import com.socialcoding.api.base.assembler.ObjectAssembler;
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
    private CctvQueryService cctvQueryService;
	@Autowired
	private CctvCommandService cctvCommandService;
    @Autowired
    private CommentService commentService;

    public CctvDetailResult getCctvDetail(Long cctvId) {
        Cctv cctv = cctvQueryService.getCctvById(cctvId);
		CctvDto cctvDto = null;
		if (cctv instanceof PublicCctv) {
			cctvDto = assembler.assemble(cctv, PublicCctvDto.class);
		} else if (cctv instanceof PrivateCctv) {
			cctvDto = assembler.assemble(cctv, PrivateCctvDto.class);
		}

//        List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
//        CommentBundleDto commentBundleDto = new CommentBundleDto();
//        commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
//        commentBundleDto.setComments(assembler.assemble(comments, CommentDto.class));

        CctvDetailResult cctvDetailResult = new CctvDetailResult();
        cctvDetailResult.setStatus(ResponseStatus.SUCCESS);
        cctvDetailResult.setCctv(cctvDto);
//        cctvDetailResult.setComments(commentBundleDto);
        return cctvDetailResult;
    }

    public MapCctvResult listCctvBetween(MapPositionForm positions) {
        Position southWest = Position.of(positions.getSouth(), positions.getWest());
        Position northEast = Position.of(positions.getNorth(), positions.getEast());
        List<Cctv> cctvs = cctvQueryService.listCctvBetween(southWest, northEast);
        List<CctvOverviewDto> cctvOverviewDtos = assembler.assemble(cctvs, CctvOverviewDto.class);

        MapCctvResult mapCctvResult = new MapCctvResult();
        mapCctvResult.setStatus(ResponseStatus.SUCCESS);
        mapCctvResult.setCctvs(cctvOverviewDtos);
        return mapCctvResult;
    }

    public CctvRegistrationResult registerPrivateCctv(CctvRegistrationForm form, MultipartFile cctvImage, MultipartFile noticeImage) {
		Cctv cctv = cctvCommandService.registerPrivateCctv(form, cctvImage, noticeImage);

		CctvRegistrationResult result = new CctvRegistrationResult();
        result.setStatus(ResponseStatus.SUCCESS);
        result.setCctvId(cctv.getCctvId());
        return result;
    }

	public MapCctvCountResult countCctvBetween(MapPositionForm positions) {
		Position southWest = Position.of(positions.getSouth(), positions.getWest());
		Position northEast = Position.of(positions.getNorth(), positions.getEast());
    	long count = cctvQueryService.countCctvBetween(southWest, northEast);
    	return MapCctvCountResult.success(count);
	}
}

package com.socialcoding.api.cctv.service;

import com.socialcoding.api.cctv.dto.request.MapPositionDto;
import com.socialcoding.api.cctv.dto.response.CctvDetailDto;
import com.socialcoding.api.cctv.dto.response.CctvDto;
import com.socialcoding.api.cctv.dto.response.CctvOverviewDto;
import com.socialcoding.api.cctv.dto.response.MapCctvDto;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.comment.dto.response.CommentBundleDto;
import com.socialcoding.api.comment.dto.response.CommentDto;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.comment.service.CommentService;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.assembler.ObjectAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CctvFacadeService {
    @Autowired
    private ObjectAssembler assembler;
    @Autowired
    private CctvService cctvService;
    @Autowired
    private CommentService commentService;

    public CctvDetailDto getCctvDetail(Long cctvId) {
        Cctv cctv = cctvService.getCctvById(cctvId);
        CctvDto cctvDto = assembler.assemble(cctv, CctvDto.class);

        List<Comment> comments = commentService.getCommentsByCctvIdWithFirstPage(cctvId);
        CommentBundleDto commentBundleDto = new CommentBundleDto();
        commentBundleDto.setNextCommentId(commentService.getNextRequestCommentId(comments));
        commentBundleDto.setComments(assembler.assemble(comments, CommentDto.class));

        CctvDetailDto cctvDetailDto = new CctvDetailDto();
        cctvDetailDto.setStatus(ResponseStatus.SUCCESS);
        cctvDetailDto.setCctv(cctvDto);
        cctvDetailDto.setComments(commentBundleDto);
        return cctvDetailDto;
    }

    public MapCctvDto listCctvBetween(MapPositionDto positions) {
        Position southWest = Position.of(positions.getSouth(), positions.getWest());
        Position northEast = Position.of(positions.getNorth(), positions.getEast());
        List<Cctv> cctvs = cctvService.listCctvBetween(southWest, northEast);
        List<CctvOverviewDto> cctvOverviewDtos = assembler.assemble(cctvs, CctvOverviewDto.class);

        MapCctvDto mapCctvDto = new MapCctvDto();
        mapCctvDto.setStatus(ResponseStatus.SUCCESS);
        mapCctvDto.setCctvs(cctvOverviewDtos);
        return mapCctvDto;
    }
}

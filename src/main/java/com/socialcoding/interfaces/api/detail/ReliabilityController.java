package com.socialcoding.interfaces.api.detail;

import com.google.common.collect.Lists;
import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Reliability;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.domain.services.reliability.ReliabilityService;
import com.socialcoding.domain.services.reliability.ReliablePoint;
import com.socialcoding.interfaces.dtos.Request.ReliabilityApplyDto;
import com.socialcoding.interfaces.dtos.Response.ReliabilityDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@RestController
public class ReliabilityController {
    private static final ModelMapper MAPPER = new ModelMapper();

    @Autowired
    private CctvService cctvService;
    @Autowired
    private ReliabilityService reliabilityService;

    @RequestMapping(value = "/cctv/{cctvId}/reliability", method = RequestMethod.GET)
    public Map<String, Object> getReliabilities(@PathVariable Long cctvId) {
        Cctv cctv = cctvService.getCctvDetailById(cctvId);
        //TODO handle null cctv
        ReliabilityDto reliabilityDto = new ReliabilityDto();
        reliabilityDto.setCorrectPoint(reliabilityService.getCorrectPoint(cctv.getReliabilities()));
        reliabilityDto.setIncorrectPoint(reliabilityService.getIncorrectPoint(cctv.getReliabilities()));

        //TODO 현재 유저의 선택 정보 넘기기
        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("reliability", reliabilityDto);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/reliability", method = RequestMethod.POST)
    public Map<String, Object> selectReliability(@PathVariable Long cctvId, @Valid ReliabilityApplyDto reliabilityApplyDto) {
        Cctv cctv = cctvService.getCctvById(cctvId);
        Reliability reliability = MAPPER.map(reliabilityApplyDto, Reliability.class);
        reliability.setCctv(cctv);
        Reliability currentUserReliability = reliabilityService.applyReliability(reliability);
        ReliablePoint reliablePoint = reliabilityService.getReliablePointByCctvId(cctvId);

        ReliabilityDto reliabilityDto = new ReliabilityDto();
        reliabilityDto.setCorrectPoint(reliablePoint.getCorrectPoint());
        reliabilityDto.setIncorrectPoint(reliablePoint.getIncorrectPoint());

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("reliability", reliabilityDto);
                put("currentUserReliability", currentUserReliability);
            }
        };
    }
}

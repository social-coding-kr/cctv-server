package com.socialcoding.interfaces.api.detail;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Reliability;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.domain.services.reliability.ReliabilityService;
import com.socialcoding.domain.services.reliability.TotalReliability;
import com.socialcoding.interfaces.dtos.ObjectMapper;
import com.socialcoding.interfaces.dtos.Request.ReliabilitySelectDto;
import com.socialcoding.interfaces.dtos.Response.TotalReliabilityDto;
import com.socialcoding.interfaces.dtos.Response.UserReliabilityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@RestController
public class ReliabilityController {
    @Autowired
    private CctvService cctvService;
    @Autowired
    private ReliabilityService reliabilityService;

    @RequestMapping(value = "/cctv/{cctvId}/reliability", method = RequestMethod.GET)
    public Map<String, Object> getReliabilities(@PathVariable Long cctvId) {
        Cctv cctv = cctvService.getCctvDetailById(cctvId);
        //TODO handle null cctv
        TotalReliabilityDto totalReliabilityDto = new TotalReliabilityDto();
        totalReliabilityDto.setCorrectReliability(reliabilityService.getCorrectReliability(cctv.getReliabilities()));
        totalReliabilityDto.setIncorrectReliability(reliabilityService.getIncorrectReliability(cctv.getReliabilities()));

        //TODO 현재 유저의 선택 정보 넘기기
        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("reliability", totalReliabilityDto);
            }
        };
    }

    @RequestMapping(value = "/cctv/{cctvId}/reliability", method = RequestMethod.POST)
    public Map<String, Object> selectReliability(@PathVariable Long cctvId, @RequestBody @Valid ReliabilitySelectDto reliabilitySelectDto) {
        Cctv cctv = cctvService.getCctvById(cctvId);
        Reliability reliability = ObjectMapper.map(reliabilitySelectDto, cctv);
        Reliability userReliability = reliabilityService.selectReliability(reliability);
        TotalReliability totalReliability = reliabilityService.getTotalReliabilityByCctvId(cctvId);

        TotalReliabilityDto totalReliabilityDto = new TotalReliabilityDto();
        totalReliabilityDto.setCorrectReliability(totalReliability.getCorrectReliability());
        totalReliabilityDto.setIncorrectReliability(totalReliability.getIncorrectReliability());

        UserReliabilityDto userReliabilityDto = ObjectMapper.map(userReliability, UserReliabilityDto.class);

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("totalReliability", totalReliabilityDto);
                put("currentUserReliability", userReliabilityDto);
            }
        };
    }
}

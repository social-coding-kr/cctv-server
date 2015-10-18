package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.request.MapPositionDto;
import com.socialcoding.api.cctv.dto.response.MapCctvDto;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.service.CctvService;
import com.socialcoding.api.cctv.model.Position;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.assemble.ObjectMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CctvMapController {
    private static final Type TYPE_MAP_CCTV_DTO = new TypeToken<List<MapCctvDto>>() {}.getType();

    @Autowired
    private CctvService cctvService;

    @RequestMapping(value = "/map/cctvs", method = RequestMethod.GET)
    public Map<String, Object> getCctvs(@Valid MapPositionDto positions) {
        Position southWest = Position.of(positions.getSouth(), positions.getWest());
        Position northEast = Position.of(positions.getNorth(), positions.getEast());
        List<Cctv> cctvs = cctvService.listCctvBetween(southWest, northEast);
        List<MapCctvDto> mapCctvDtos = ObjectMapper.map(cctvs, TYPE_MAP_CCTV_DTO);
        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
                put("cctvs", mapCctvDtos);
            }
        };
    }
}

package com.socialcoding.interfaces.api.map;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.interfaces.dtos.ObjectMapper;
import com.socialcoding.interfaces.dtos.Request.MapPositionDto;
import com.socialcoding.interfaces.dtos.Response.MapCctvDto;
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

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@RestController
public class CctvMapController {
    private static final Type TYPE_MAP_CCTV_DTO = new TypeToken<List<MapCctvDto>>() {}.getType();

    @Autowired
    private CctvService cctvService;

    @RequestMapping(value = "/map/cctvs", method = RequestMethod.GET)
    public Map<String, Object> getCctvs(@Valid MapPositionDto positions) {
        List<Cctv> cctvs = cctvService.listCctvsBetween(positions.getNorthEast(), positions.getSouthWest());
        List<MapCctvDto> mapCctvDtos = ObjectMapper.map(cctvs, TYPE_MAP_CCTV_DTO);
        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctvs", mapCctvDtos);
            }
        };
    }
}

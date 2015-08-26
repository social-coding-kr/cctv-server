package com.socialcoding.interfaces.api.map;

import com.google.common.collect.Lists;
import com.socialcoding.interfaces.dtos.Request.MapPositionDto;
import com.socialcoding.interfaces.dtos.Response.CctvOverviewDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@RestController
public class CctvMapController {
    @RequestMapping(value = "/map/cctvs", method = RequestMethod.GET)
    public Map<String, Object> getCctvs(@Valid MapPositionDto position) {
        //TODO add logic
        CctvOverviewDto badCctv = new CctvOverviewDto();
        badCctv.setCctvId(10L);
        badCctv.setLatitude(37.481200d);
        badCctv.setLongitude(126.952514d);

        CctvOverviewDto goodCctv = new CctvOverviewDto();
        goodCctv.setCctvId(10L);
        goodCctv.setLatitude(37.478216d);
        goodCctv.setLongitude(126.951527d);

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctvs", Lists.newArrayList(badCctv, goodCctv));
            }
        };
    }
}

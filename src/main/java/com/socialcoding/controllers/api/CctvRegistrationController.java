package com.socialcoding.controllers.api;

import com.google.common.collect.Lists;
import com.socialcoding.controllers.dto.Response.CctvValidationDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.socialcoding.controllers.dto.Response.ResponseStatus.SUCCESS;

@RestController
public class CctvRegistrationController {
    @RequestMapping(value = "/cctv/validate", method = RequestMethod.GET)
    public Map<String, Object> validateNewCctv(Double latitude, Double longitude) {
        if (latitude == 10d) {
            return new HashMap<String, Object>() {
                {
                    put("status", SUCCESS);
                    put("cctvs", Lists.newArrayList());
                }
            };
        }

        CctvValidationDto validation10 = new CctvValidationDto();
        validation10.setCctvId(10);
        validation10.setCctvImage("https://pixabay.com/static/uploads/photo/2013/07/13/09/46/surveillance-155982_640.png");

        CctvValidationDto validation20 = new CctvValidationDto();
        validation20.setCctvId(20);
        validation20.setCctvImage("https://pixabay.com/static/uploads/photo/2013/07/13/09/46/surveillance-155982_640.png");

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctvs", Lists.newArrayList(validation10, validation20));
            }
        };
    }
}

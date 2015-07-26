package com.socialcoding.controller.api;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CctvRegistrationController {
    @RequestMapping(value = "/cctv/validate", method = RequestMethod.GET)
    public Map<String, Object> validateNewCctv(Double latitude, Double longitude) {
        return Maps.newHashMap();
    }
}

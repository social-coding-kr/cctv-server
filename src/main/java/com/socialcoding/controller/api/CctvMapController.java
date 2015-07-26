package com.socialcoding.controller.api;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CctvMapController {
    @RequestMapping(value = "/map/cctvs", method = RequestMethod.GET)
    public Map<String, Object> getCctvs(Long tileId, Long reliability) {
        return Maps.newHashMap();
    }
}

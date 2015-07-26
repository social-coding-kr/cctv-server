package com.socialcoding.controller.api;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CctvDetailController {
    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public Map<String, Object> getCctv(@PathVariable Long cctvId) {
        return Maps.newHashMap();
    }

    @RequestMapping(value = "/cctv/{cctvId}/comments", method = RequestMethod.GET)
    public Map<String, Object> getComments(@PathVariable Long cctvId) {
        return Maps.newHashMap();
    }
}

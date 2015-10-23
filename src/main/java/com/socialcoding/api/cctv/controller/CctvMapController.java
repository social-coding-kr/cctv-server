package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.request.MapPositionDto;
import com.socialcoding.api.cctv.dto.response.MapCctvResult;
import com.socialcoding.api.cctv.service.CctvFacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CctvMapController {
    @Autowired
    private CctvFacadeService cctvFacadeService;

    @RequestMapping(value = "/map/cctvs", method = RequestMethod.GET)
    public MapCctvResult getCctvs(@Valid MapPositionDto positions) {
        return cctvFacadeService.listCctvBetween(positions);
    }
}

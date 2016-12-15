package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.request.MapPositionForm;
import com.socialcoding.api.cctv.dto.response.MapCctvCountResult;
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
    public MapCctvResult getCctvs(@Valid MapPositionForm positions) {
        return cctvFacadeService.listCctvBetween(positions);
    }

    @RequestMapping(value = "/map/cctvs/counts", method = RequestMethod.GET)
	public MapCctvCountResult getCctvCounts(@Valid MapPositionForm positions) {
		return cctvFacadeService.countCctvBetween(positions);
	}

}

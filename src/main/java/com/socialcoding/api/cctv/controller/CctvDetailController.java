package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.response.CctvDetailDto;
import com.socialcoding.api.cctv.service.CctvFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CctvDetailController {
    @Autowired
    private CctvFacadeService cctvFacadeService;

    @RequestMapping(value = "/cctv/{cctvId}", method = RequestMethod.GET)
    public CctvDetailDto getCctv(@PathVariable Long cctvId) {
        return cctvFacadeService.getCctvDetail(cctvId);
    }
}

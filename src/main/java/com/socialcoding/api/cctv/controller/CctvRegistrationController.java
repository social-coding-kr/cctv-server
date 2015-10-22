package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationDto;
import com.socialcoding.api.cctv.dto.response.CctvRegistrationResult;
import com.socialcoding.api.cctv.service.CctvFacadeService;
import com.socialcoding.api.common.validation.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
public class CctvRegistrationController {
    @Autowired
    private CctvFacadeService cctvFacadeService;

    @RequestMapping(value = "/cctv", method = RequestMethod.POST)
    public CctvRegistrationResult registerCctv(
            @Valid CctvRegistrationDto cctvRegistrationDto,
            @RequestParam("cctvImage") MultipartFile cctvImage,
            @RequestParam(value = "noticeImage", required = false) MultipartFile noticeImage) {

        Validations.validateImageType(cctvImage);
        Validations.validateImageType(noticeImage);

        return cctvFacadeService.registerCctv(cctvRegistrationDto, cctvImage, noticeImage);

    }
}

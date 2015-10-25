package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
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
    public CctvRegistrationResult registerPrivateCctv(
		@Valid CctvRegistrationForm cctvRegistrationForm,
		@RequestParam("cctvImage") MultipartFile cctvImage,
		@RequestParam(value = "noticeImage", required = false) MultipartFile noticeImage) {

        Validations.validateImage(cctvImage);

        return cctvFacadeService.registerPrivateCctv(cctvRegistrationForm, cctvImage, noticeImage);

    }
}

package com.socialcoding.api.cctv.controller;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationDto;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.cctv.service.CctvService;
import com.socialcoding.api.common.ResponseStatus;
import com.socialcoding.api.common.validation.Validations;
import com.socialcoding.api.common.assemble.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CctvRegistrationController {
    @Autowired
    private CctvService cctvService;

    @RequestMapping(value = "/cctv", method = RequestMethod.POST)
    public Map<String, Object> registerCctv(@Valid CctvRegistrationDto cctvRegistrationDto,
                                            @RequestParam("cctvImage") MultipartFile cctvImage,
                                            @RequestParam(value = "noticeImage", required = false) MultipartFile noticeImage) {

        Validations.validateImageType(cctvImage);
        Validations.validateImageType(noticeImage);

        Cctv cctv = ObjectMapper.map(cctvRegistrationDto);
        //TODO save image file
        Cctv registeredCctv = cctvService.registerCctv(cctv);

        return new HashMap<String, Object>() {
            {
                put("status", ResponseStatus.SUCCESS);
                put("cctvId", registeredCctv.getCctvId());
            }
        };
    }
}

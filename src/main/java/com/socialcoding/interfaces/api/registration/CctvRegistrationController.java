package com.socialcoding.interfaces.api.registration;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.interfaces.api.registration.validation.Validations;
import com.socialcoding.interfaces.dtos.ObjectMapper;
import com.socialcoding.interfaces.dtos.Request.CctvRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

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
                put("status", SUCCESS);
                put("cctvId", registeredCctv.getCctvId());
            }
        };
    }
}

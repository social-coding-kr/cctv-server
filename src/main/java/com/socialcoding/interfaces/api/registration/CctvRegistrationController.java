package com.socialcoding.interfaces.api.registration;

import com.google.common.collect.Lists;
import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.services.cctv.CctvService;
import com.socialcoding.interfaces.dtos.Request;
import com.socialcoding.interfaces.dtos.Request.CctvRegistrationDto;
import com.socialcoding.interfaces.dtos.Response.CctvValidationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.socialcoding.interfaces.dtos.Response.ResponseStatus.SUCCESS;

@RestController
public class CctvRegistrationController {
    private static final ModelMapper MAPPER = new ModelMapper();

    @Autowired
    private CctvService cctvService;

    @RequestMapping(value = "/cctv/validate", method = RequestMethod.GET)
    public Map<String, Object> validateNewCctv(Double latitude, Double longitude) {
        if (latitude == 10d) {
            return new HashMap<String, Object>() {
                {
                    put("status", SUCCESS);
                    put("cctvs", Lists.newArrayList());
                }
            };
        }

        CctvValidationDto validation10 = new CctvValidationDto();
        validation10.setCctvId(10);
        validation10.setCctvImage("https://pixabay.com/static/uploads/photo/2013/07/13/09/46/surveillance-155982_640.png");

        CctvValidationDto validation20 = new CctvValidationDto();
        validation20.setCctvId(20);
        validation20.setCctvImage("https://pixabay.com/static/uploads/photo/2013/07/13/09/46/surveillance-155982_640.png");

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctvs", Lists.newArrayList(validation10, validation20));
            }
        };
    }

    @RequestMapping(value = "/cctv", method = RequestMethod.POST)
    public Map<String, Object> registerCctv(@Valid CctvRegistrationDto cctvRegistrationDto) {
        //TODO check image mapping
        Cctv cctv = MAPPER.map(cctvRegistrationDto, Cctv.class);
        //TODO save image file
        Cctv registeredCctv = cctvService.register(cctv);

        return new HashMap<String, Object>() {
            {
                put("status", SUCCESS);
                put("cctvId", registeredCctv.getCctvId());
            }
        };
    }
}

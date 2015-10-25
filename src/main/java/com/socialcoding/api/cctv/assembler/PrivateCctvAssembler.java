package com.socialcoding.api.cctv.assembler;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
import com.socialcoding.api.cctv.model.PrivateCctv;
import com.socialcoding.api.common.assembler.AbstractAssembler;
import org.springframework.stereotype.Component;

@Component
public class PrivateCctvAssembler extends AbstractAssembler<CctvRegistrationForm, PrivateCctv> {
    @Override
    protected PrivateCctv doAssemble(CctvRegistrationForm dto) {
        PrivateCctv cctv = new PrivateCctv();
        cctv.setLatitude(dto.getLatitude());
        cctv.setLongitude(dto.getLongitude());
        cctv.setPurpose(dto.getPurpose());
        return cctv;
    }
}

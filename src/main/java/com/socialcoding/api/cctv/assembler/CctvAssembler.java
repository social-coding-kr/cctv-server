package com.socialcoding.api.cctv.assembler;

import com.socialcoding.api.cctv.dto.request.CctvRegistrationForm;
import com.socialcoding.api.cctv.model.Cctv;
import com.socialcoding.api.common.assembler.AbstractAssembler;
import org.springframework.stereotype.Component;

@Component
public class CctvAssembler extends AbstractAssembler<CctvRegistrationForm, Cctv> {
    @Override
    protected Cctv doAssemble(CctvRegistrationForm dto) {
        Cctv cctv = new Cctv();
        cctv.setLatitude(dto.getLatitude());
        cctv.setLongitude(dto.getLongitude());
        cctv.setPurpose(dto.getPurpose());
        cctv.setCreatedBy(dto.getUserId());
        cctv.setModifiedBy(dto.getUserId());
        return cctv;
    }
}

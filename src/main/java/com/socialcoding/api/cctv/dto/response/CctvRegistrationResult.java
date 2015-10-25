package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.base.dto.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CctvRegistrationResult extends AbstractResponse {
    private Long cctvId;
}

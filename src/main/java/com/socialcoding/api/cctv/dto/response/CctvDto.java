package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.cctv.model.CctvSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CctvDto {
    private long cctvId;
    private String purpose;
    private CctvSource source;
}

package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.cctv.model.CctvSource;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CctvOverviewDto {
    private long cctvId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private CctvSource source;
}

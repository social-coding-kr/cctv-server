package com.socialcoding.api.cctv.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MapCctvDto {
    private long cctvId;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String purpose;
}

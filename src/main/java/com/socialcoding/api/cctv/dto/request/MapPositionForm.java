package com.socialcoding.api.cctv.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class MapPositionForm {
    @NotNull
    private BigDecimal south;
    @NotNull
    private BigDecimal west;
    @NotNull
    private BigDecimal north;
    @NotNull
    private BigDecimal east;
}

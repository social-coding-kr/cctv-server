package com.socialcoding.api.cctv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;

    public static Position of(BigDecimal latitude, BigDecimal longitude) {
        return new Position(latitude, longitude);
    }
}

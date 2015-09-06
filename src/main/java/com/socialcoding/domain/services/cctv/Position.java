package com.socialcoding.domain.services.cctv;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;

    public static Position of(double latitude, double longitude) {
        return new Position(latitude, longitude);
    }
}

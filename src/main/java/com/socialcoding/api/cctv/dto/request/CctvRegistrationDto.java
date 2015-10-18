package com.socialcoding.api.cctv.dto.request;

import com.socialcoding.api.cctv.model.CctvPurpose;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CctvRegistrationDto {
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;
    @NotNull
    private CctvPurpose purpose;
    @NotBlank
    private String userId;
}

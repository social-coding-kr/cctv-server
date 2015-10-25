package com.socialcoding.api.cctv.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class CctvRegistrationForm {
    @NotNull
    private BigDecimal latitude;

    private BigDecimal longitude;
    @NotNull
    private String purpose;
    @NotBlank
    private String userId;
}

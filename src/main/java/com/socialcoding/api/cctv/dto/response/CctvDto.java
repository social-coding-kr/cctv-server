package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.cctv.model.CctvSource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CctvDto {
    private long cctvId;
    private String address;
    private String cctvImage;
    private String noticeImage;
    private String purpose;
    private CctvSource source;
}

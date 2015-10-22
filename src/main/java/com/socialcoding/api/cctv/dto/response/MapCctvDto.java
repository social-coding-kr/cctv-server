package com.socialcoding.api.cctv.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapCctvDto extends AbstractResponse {
    private List<CctvOverviewDto> cctvs;
}

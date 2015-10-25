package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.common.dto.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MapCctvResult extends AbstractResponse {
    private List<CctvOverviewDto> cctvs;
}

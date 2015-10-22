package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.common.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {
    private ResponseStatus status;
}

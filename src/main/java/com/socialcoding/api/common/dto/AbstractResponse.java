package com.socialcoding.api.common.dto;

import com.socialcoding.api.common.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {
    private ResponseStatus status;
}

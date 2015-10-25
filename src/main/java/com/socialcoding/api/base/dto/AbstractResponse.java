package com.socialcoding.api.base.dto;

import com.socialcoding.api.base.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractResponse {
    private ResponseStatus status;
}

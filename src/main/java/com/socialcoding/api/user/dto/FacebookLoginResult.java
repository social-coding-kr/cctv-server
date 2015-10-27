package com.socialcoding.api.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookLoginResult {
    private String code;
    private String error;
}

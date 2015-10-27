package com.socialcoding.api.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FacebookTokenResult {
    private String access_token;
    private String token_type;
    private Long expires_in;
}

package com.socialcoding.api.user.controller;

import com.socialcoding.api.user.dto.FacebookLoginResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private FacebookConnectionFactory facebookConnectionFactory;
    @Autowired
    private OAuth2Parameters facebookOAuthParameters;

    @RequestMapping("/auth")
    public void auth(FacebookLoginResult result) {
        //TODO code 뒤가 잘리는문제확인
        log.info("code: {}", result.getCode());
        OAuth2Operations operations = facebookConnectionFactory.getOAuthOperations();
        AccessGrant accessGrant = operations.exchangeForAccess(result.getCode() + "#_=_", "http://localhost:8099/auth", null);
        log.info("token: {}", accessGrant.getAccessToken());
        log.info("expired: {}", accessGrant.getExpireTime());
        //TODO 3. save token with login id
        //TODO 4. context holder
    }
}

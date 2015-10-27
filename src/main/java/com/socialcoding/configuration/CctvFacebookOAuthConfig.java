package com.socialcoding.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Parameters;

@Configuration
@ConfigurationProperties(prefix = "auth.facebook")
public class CctvFacebookOAuthConfig {
    private String appId;
    private String appSecret;

    @Bean
    public FacebookConnectionFactory facebookConnectionFactory() {
        return new FacebookConnectionFactory("", "");
    }

    @Bean
    public OAuth2Parameters facebookOAuthParameters() {
        OAuth2Parameters parameters = new OAuth2Parameters();
        parameters.setScope("public_profile");
        parameters.setRedirectUri("http://localhost:8099/auth");
        return parameters;
    }
}

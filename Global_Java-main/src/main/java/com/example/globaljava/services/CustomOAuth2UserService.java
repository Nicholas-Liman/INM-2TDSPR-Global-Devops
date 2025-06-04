package com.example.globaljava.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User user = super.loadUser(userRequest);

        Map<String, Object> attributes = user.getAttributes();

        return new org.springframework.security.oauth2.core.user.DefaultOAuth2User(
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),
                attributes,
                "login"
        );
    }
}

package com.energizeglobal.itpm.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CustomOAuth2User extends DefaultOAuth2User {

    private String email;
    private String id;

    /**
     * Constructs a {@code OAuth2User} using the provided parameters.
     *
     * @param user        default OAuth2User provided by spring Oauth2 security
     * @param authorities the authorities granted to the user
     */
    public CustomOAuth2User(OAuth2User user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities, user.getAttributes(), "name");
    }
}

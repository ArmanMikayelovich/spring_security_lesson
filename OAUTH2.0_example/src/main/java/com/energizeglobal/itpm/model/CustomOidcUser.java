package com.energizeglobal.itpm.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class CustomOidcUser extends DefaultOidcUser {

    private String email;
    private String id;

    public CustomOidcUser(Collection<? extends GrantedAuthority> authorities, OidcIdToken idToken) {
        super(authorities, idToken);
    }


}

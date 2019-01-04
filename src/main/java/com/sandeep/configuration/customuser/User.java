package com.sandeep.configuration.customuser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Authentication {

    private String credentials;
    private String details;
    private String principal;
    private boolean authenticated;
    private String name;


    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singletonList((GrantedAuthority) () -> "USER");
    }
}

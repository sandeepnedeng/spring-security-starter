package com.sandeep.configuration.customuser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomAuthProvider implements AuthenticationProvider {


    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();

        log.info("Requesting access to login as: {}", name);

        return User.builder()
                .authenticated(true)
                .principal("sandeep")
                .name("sandeep")
                .build();
    }


    public boolean supports(Class<?> aClass) {

        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}

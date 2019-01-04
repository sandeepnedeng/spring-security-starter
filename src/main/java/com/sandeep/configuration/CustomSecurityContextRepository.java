package com.sandeep.configuration;

import com.sandeep.configuration.customuser.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomSecurityContextRepository implements SecurityContextRepository {

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder httpRequestResponseHolder) {

        Cookie[] cookies = httpRequestResponseHolder.getRequest().getCookies();

        for (int i = 0; cookies != null && i < cookies.length; i++) {

            if (cookies[i].getName().equals("SANDEEP") &&
                    cookies[i].getValue().equals("SnakeKing")) {

                return createSecurityContext();
            }
        }

        return SecurityContextHolder.createEmptyContext();
    }


    private SecurityContext createSecurityContext() {

        SecurityContext securityCxt = new SecurityContext() {

            private Authentication authentication;


            @Override
            public Authentication getAuthentication() {

                return authentication;
            }


            @Override
            public void setAuthentication(Authentication authentication) {

                this.authentication = authentication;
            }
        };

        securityCxt.setAuthentication(createAuthentication());
        return securityCxt;
    }


    private Authentication createAuthentication() {

        Authentication authentication = User.builder()
                .principal("snakeKing")
                .name("snakeKing")
                .authenticated(true)
                .details("details")
                .build();

        return authentication;
    }


    @Override
    public void saveContext(SecurityContext securityContext, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

    }


    @Override
    public boolean containsContext(HttpServletRequest httpServletRequest) {

        Cookie[] cookies = httpServletRequest.getCookies();

        for (int i = 0; cookies != null && i < cookies.length; i++) {

            if (cookies[i].getName().equals("SANDEEP") &&
                    cookies[i].getValue().equals("SnakeKing")) {

                return true;
            }
        }

        return false;
    }
}

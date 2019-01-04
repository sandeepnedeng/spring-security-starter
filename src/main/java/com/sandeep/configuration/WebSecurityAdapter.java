package com.sandeep.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

    private SecurityContextRepository customSecurityContextRepository;
    private AuthenticationEntryPoint customAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .securityContext()
                .securityContextRepository(customSecurityContextRepository)
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                ;
    }


    @Bean
    public SimpleMappingExceptionResolver exceptionResolver() {

        SimpleMappingExceptionResolver exResolver = new SimpleMappingExceptionResolver();
        exResolver.setExcludedExceptions(AccessDeniedException.class);
        return exResolver;
    }
}
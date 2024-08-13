package com.jnep.backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.jnep.backend.security.auth.MemberPrincipalDetailsService;
import com.jnep.backend.security.provider.MemberAuthenticatorProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MemberSecurityConfig_back {
    
    @Autowired
    MemberAuthenticatorProvider memberAuthenticatorProvider;

    @Autowired
    MemberPrincipalDetailsService memberPrincipalDetailsService;

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(memberAuthenticatorProvider);
    }
}

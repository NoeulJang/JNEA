package com.jnep.backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jnep.backend.security.auth.MemberPrincipalDetailsService;
import com.jnep.backend.security.provider.MemberAuthenticatorProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class MemberSecurityConfig {
    
    @Autowired
    MemberAuthenticatorProvider memberAuthenticatorProvider;
    
    @Autowired
    MemberPrincipalDetailsService memberPrincipalDetailsService;

    @Autowired
    public void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(memberAuthenticatorProvider);
    }
    
    @Bean
    public SecurityFilterChain memberSecurityFilterChain (HttpSecurity http) throws Exception{

        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        return http
            .csrf(csrf -> csrf
                .csrfTokenRequestHandler(requestHandler)
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers("/", "/bootstrap/**", "/logout/**", "/login/**", "/join/**")
            )
            .authorizeHttpRequests(request -> request
                .requestMatchers("/bootstrap/**").permitAll()   //resources 의 static 경로 모두 허용
                .anyRequest().permitAll()
            )
            .formLogin(customizer -> customizer
                .loginPage("/login")
                .usernameParameter("username")
                .successHandler(new MemberAuthSuccessHandler())
                .failureHandler(new MemberAuthFailureHandler())
                .permitAll()
            )
            .rememberMe(customizer -> customizer
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(60*60*24*7)
                .userDetailsService(memberPrincipalDetailsService)
                .authenticationSuccessHandler(new MemberAuthSuccessHandler())
            )
            .logout(customizer -> customizer
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("remember-me")
                .deleteCookies("JSESSIONID")
                .permitAll()
            )
            .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults())
            .build();
        
    }
}
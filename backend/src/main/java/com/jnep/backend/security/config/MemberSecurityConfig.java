package com.jnep.backend.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jnep.backend.security.auth.MemberPrincipalDetailsService;
import com.jnep.backend.security.provider.MemberAuthenticatorProvider;

import jakarta.servlet.DispatcherType;
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
    public void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(memberAuthenticatorProvider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/", "/login/**","/logout","/bootstrap/**","/*.ico","/img/**","/layout/**","/item/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> {
                            try {
                                login
                                        .loginPage("/login").permitAll()	//  로그인 페이지 지정
                                        .defaultSuccessUrl("/") // 로그인 성공 후 화면
                                        .usernameParameter("email")	//  submit할 아이디
                                        .failureUrl("/members/login/error").permitAll()
                                        .and()
                                        .logout() // exception 필요
                                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll() // 로그아웃 url
                                        .logoutSuccessUrl("/"); // 로그아웃 후 이동할 화면
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 해수함수를 이용하여 비밀번호를 암호화하여 저장.
        return new BCryptPasswordEncoder();

    }
}

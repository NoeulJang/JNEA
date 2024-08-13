package com.jnep.backend.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jnep.backend.domain.User;
import com.jnep.backend.security.auth.MemberPrincipalDetails;
import com.jnep.backend.security.auth.MemberPrincipalDetailsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberAuthenticatorProvider implements AuthenticationProvider{

    private final MemberPrincipalDetailsService memberPrincipalDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        MemberPrincipalDetails memberPrincipalDetails = (MemberPrincipalDetails) memberPrincipalDetailsService.loadUserByUsername(username);

        String dbPassword = memberPrincipalDetails.getPassword();

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if(!passwordEncoder.matches(password, dbPassword)){
            throw new BadCredentialsException("아이디 또는 비밀번호가 일치하지 않습니다.");
        }
        
        User user = memberPrincipalDetails.getUser();
        
        if(user == null){
            throw new BadCredentialsException("사용할 수 없는 계정입니다.");
        }

        return new UsernamePasswordAuthenticationToken(memberPrincipalDetails, null, memberPrincipalDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}

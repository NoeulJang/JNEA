package com.jnep.backend.security.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.jnep.backend.domain.User;

public class MemberPrincipalDetails implements UserDetails{

    private final User user;

    public MemberPrincipalDetails(User user) {
        this.user = user;
    }

    public User getUser(){
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("user"));
        
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return user.getUserPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getUserNickName();
    }
    
    public String getUserId() {
        return user.getUserId();
    }

    public Long getUserSeq() {
        return user.getUserSeq();
    }
    
}

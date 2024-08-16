package com.jnep.backend.security.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jnep.backend.domain.User;
import com.jnep.backend.repository.LoginRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberPrincipalDetailsService implements UserDetailsService {
    
    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = loginRepository.findByUserId(userId);

        if(user == null)
            throw new UsernameNotFoundException("존재하지 않는 계정입니다.");

        return new MemberPrincipalDetails(user);
    }
}

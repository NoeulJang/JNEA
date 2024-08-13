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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = loginRepository.findByUserId(username);

        log.info("userId :: " + user.getUserId());
        log.info("userNickName :: " + user.getUserNickName());

        if(user == null)
            throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");

        return new MemberPrincipalDetails(user);
    }
}

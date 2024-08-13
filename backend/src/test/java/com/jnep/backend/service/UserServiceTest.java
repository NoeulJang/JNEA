package com.jnep.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import com.jnep.backend.domain.User;
import com.jnep.backend.repository.UserRepository;

import jakarta.persistence.EntityManager;


@WebAppConfiguration
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 회원가입() throws Exception{
        //given
        User user = new User();
        user.setUserId("noeul");

        //when
        Long savedSeq = userService.join(user);

        //then
        assertEquals(user, userRepository.findOne(savedSeq));
    }

    @Test
    public void 중복회원체크() throws Exception{
        //given
        User user = new User();
        user.setUserId("noeul");
        
        User user2 = new User();
        user2.setUserId("noeul");

        //when
        Long savedSeq = userService.join(user);
        try {
            Long savedSeq2 = userService.join(user2);
        } catch (IllegalTransactionStateException e) {
            // TODO: handle exception
            return;
        }

        fail("예외발생!!");
    }
}

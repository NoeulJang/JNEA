package com.jnep.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.annotation.Transactional;

import com.jnep.backend.domain.User;
import com.jnep.backend.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
//Spring Transcational 사용 권장
//readOnly true 를 사용하여 조회 시 더티체킹 x, 영속성 컨텍스트를 플러시 하지 않는다, DB에 따라서 리소스를 최적화 시키는 등의 역할
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    //의존성 주입에 알맞지 않은 방식, 테스트 시 까다로움
    //@Autowired
    //private UserRepository userRepository;
    

    //final 사용 권장
    private final UserRepository userRepository;

    //스프링이 자동으로 Autowired 해줌
    //@Autowired
    // public UserService(UserRepository userRepository){
    //     this.userRepository = userRepository;
    // }

    //위의 장점들을 lombok의 @RequiredArgsConstructor 가 처리해줌

    /*
     * 회원 가입
    */
    @Transactional
    public Long join (User user){
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserSeq();
    }

    /*
     * 회원 아이디 중복 체크
     */
    //실무에서 어쩌다 두명의 사용자가 동시에 같은 아이디를 사용 시 발생할 에러를 대비하여
    //DB에 unique 제약 조건을 걸어 주는 것이 좋음
    private void validateDuplicateUser(User user){
        List<User> byIds = userRepository.findById(user.getUserId());
        if(!byIds.isEmpty()) throw new IllegalTransactionStateException("이미 존재하는 아이디입니다.");
    }

    /*
     * 회원 전체 조회
     */
    public List<User> findUsers() {
        return userRepository.findAll();
    }
    
    /*
     * 회원 단건 조회
     */
    public User findOne(Long userSeq) {
        return userRepository.findOne(userSeq);
    }

}

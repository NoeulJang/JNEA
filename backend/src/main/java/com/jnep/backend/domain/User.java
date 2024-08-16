package com.jnep.backend.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User {

    @Id
    @Column(name = "USER_SEQ")
    @GeneratedValue
    private Long userSeq;

    @Column(name = "USER_ID", length = 20)
    private String userId;

    @Column(name = "USER_NICK_NAME", length = 20)
    private String userNickName;

    @Column(name = "USER_PASSWORD", length = 2000)
    private String userPassword;
    
    @Column(name = "AUTH_ID", length = 20)
    private String authId;

    //나는 cat 테이블에 있는 "user" 필드에 의해서 매핑돼었다.(읽기전용)
    @OneToMany(mappedBy = "user")
    private List<Cat> cats = new ArrayList<>();


}

package com.jnep.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "CAT_INFO")
public class Cat {

    @Id
    @Column(name = "CAT_NAME", length = 20)
    private String catName;

    @Column(name = "CAT_WEIGHT", length = 3)
    private int catWeight;

    @Column(name = "CAT_SPCI", length = 200)
    private String catSpci;

    @Column(name = "CAT_DIS", length = 200)
    private String catDis;

    @Column(name = "CAT_SPT", length = 10000)
    private String catSpt;

    //연관관계의 주체로 값을 넣으면 FK값이 변경된다.
    @ManyToOne
    @JoinColumn(name = "USER_SEQ")
    private User user;
}

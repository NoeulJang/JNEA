package com.jnep.backend.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "BOARD")
public class Board {

    @Id
    @Column(name = "BOARD_SEQ")
    @GeneratedValue
    private Long id;

    @Column(name = "TITLE", length = 200)
    private String title;

    @Column(name = "CONTENT", length = 10000)
    private String content;

    @Column(name = "CRT_DT")
    private LocalDateTime crtDt;
    
    @Column(name = "CRT_USER")
    private String CRT_USER;

}

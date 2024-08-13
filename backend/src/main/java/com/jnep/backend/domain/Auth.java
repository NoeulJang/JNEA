package com.jnep.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "AUTH_MNG")
public class Auth {

    @Id
    @Column(name = "AUTH_ID", length = 20)
    private String authId;

    @Column(name = "AUTH_NM", length = 20)
    private String authNm;

    @Column(name = "AUTH_INFO", length = 200)
    private String authInfo;
}

package com.jnep.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserForm {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String userId;
    
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String userPassword;

    private String userNickName;

    private Long userSeq;


}

package com.jnep.backend.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @NotEmpty(message = "아이디를 입력해주세요.")
    private String userId;
    @NotEmpty(message = "닉네임을 입력해주세요.")
    private String userNickName;
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String userPassword;

}

package com.jnep.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jnep.backend.Util.CommUtil;
import com.jnep.backend.Util.MessageUtil;
import com.jnep.backend.domain.User;
import com.jnep.backend.form.UserForm;
import com.jnep.backend.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final UserService userService;

    @GetMapping("/user/join")
    public String goJoin(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "joinUser";
    }

    @PostMapping("/user/join")
    public String join(@Valid UserForm form, BindingResult result, Model model){

        if(result.hasErrors()){
            return "joinUser";
        }

        User user = new User();
        user.setUserId(form.getUserId());
        user.setUserNickName(form.getUserNickName());
        user.setUserPassword(form.getUserPassword());

        userService.join(user);

        CommUtil.goSuccessPage(model, MessageUtil.joinSuccess(), "메인 페이지로 돌아가기", "");

        return "comm/message";
    }

}

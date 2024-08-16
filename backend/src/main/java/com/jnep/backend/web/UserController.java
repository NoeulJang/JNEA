package com.jnep.backend.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public String goJoin(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "joinUser";
    }

    @PostMapping("/join")
    public String join(@Valid UserForm form, BindingResult result, Model model){

        if(result.hasErrors()){
            return "joinUser";
        }

        String encodedPassword = passwordEncoder.encode(form.getUserPassword());

        log.info("encodedPassword :: " + encodedPassword);

        User user = new User();
        user.setUserId(form.getUserId());
        user.setUserNickName(form.getUserNickName());
        user.setUserPassword(encodedPassword);

        userService.join(user);

        CommUtil.goSuccessPage(model, MessageUtil.joinSuccess(), "메인 페이지로 돌아가기", "");

        return "comm/message";
    }

    @GetMapping("/myPage")
    public String goMypqge(Model model, Authentication authentication) {

        String id = authentication.getName();
        userService.findById(id);
        model.addAttribute("userForm", new UserForm());
        return "myPage";
    }

}

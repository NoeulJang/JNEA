package com.jnep.backend.web;

import java.util.List;

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
import com.jnep.backend.dto.UserForm;
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
    public String goMyPqge(Model model, Authentication authentication) {

        String id = authentication.getName();

        List<User> byId = userService.findById(id);

        User user = new User(
            byId.get(0).getUserSeq()
            , byId.get(0).getUserId()
            , byId.get(0).getUserNickName()
            , byId.get(0).getUserPassword()
            , byId.get(0).getAuthId()
            , byId.get(0).getCats());

        UserForm userForm = new UserForm(user.getUserId(), null, user.getUserNickName(), user.getUserSeq());

        model.addAttribute("userForm", userForm);

        return "myPage";
    }

    @PostMapping("/myPage")
    public String updateMyPage(UserForm userForm, Model model) {

        User user = new User(null, null, userForm.getUserNickName(), null, null, null);

        userService.saveNickName(userForm.getUserSeq(), user);

        CommUtil.goSuccessPage(model, MessageUtil.UpdateSuccess(), "메인 페이지로 돌아가기", "");

        return "comm/message";
    }

}

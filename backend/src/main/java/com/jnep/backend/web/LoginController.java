package com.jnep.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.jnep.backend.form.LoginForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    
    @GetMapping("/login")
    public String goLogin(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

}

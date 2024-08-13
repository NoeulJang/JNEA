package com.jnep.backend.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {
    
    //Slf4j와 같음
    //Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    public String hello(Model model){
        log.info("main Controller");
        return "main";
    }
}
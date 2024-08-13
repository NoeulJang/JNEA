package com.jnep.backend.Util;

import org.springframework.ui.Model;

public class CommUtil {

    public CommUtil() {
        super();
        //TODO Auto-generated constructor stub
    }

    public static void goSuccessPage(Model model, String message, String subMessage, String url){
        model.addAttribute("message", message);
        model.addAttribute("subMessage", subMessage);
        model.addAttribute("returnPage", url);
    }
}

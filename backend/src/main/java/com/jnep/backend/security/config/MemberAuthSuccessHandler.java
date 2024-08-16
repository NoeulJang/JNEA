package com.jnep.backend.security.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication exception) throws IOException, ServletException {


        HttpSession session = request.getSession();

        session.removeAttribute("loginErrorMessage");
        
        setDefaultTargetUrl("/");

        super.onAuthenticationSuccess(request, response, exception);
    }
}

package com.example.handler;

import java.io.IOException;

import javax.security.auth.login.AccountExpiredException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

@Service
public class CustomLoginFailHandler implements AuthenticationFailureHandler {

    Logger loger = LoggerFactory.getLogger(this.getClass());

    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

                loger.error("Access Denied Handler");
                loger.error("Redirect....");
                
                if (exception instanceof AuthenticationServiceException) {
                    request.setAttribute("error", "존재하지 않는 사용자입니다.");
                
                } else if(exception instanceof BadCredentialsException) {
                    request.setAttribute("error", "비밀번호가 틀립니다.");
                }
                
                // 로그인 페이지로 다시 포워딩
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
                dispatcher.forward(request, response);        
    }

}
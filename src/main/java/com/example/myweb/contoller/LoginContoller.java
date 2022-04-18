package com.example.myweb.contoller;

import com.example.myweb.model.User;
import com.example.myweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class LoginContoller {
 
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "account/loginPage";
    }

    @PostMapping("/register")
    public String register(User user){
        userService.save(user);
        return "redirect:/";
    }
    
    @GetMapping("/register")
    public String register(){
        return "account/register";
    }
}

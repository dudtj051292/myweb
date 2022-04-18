package com.example.myweb.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/admin")
    public String adminHome(){
        return "/admin/index";
    }
    
}

package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String landing() {
        return "welcome";
    }

    @GetMapping("/home")
    public String welcome() {
            return "home";
    }
}

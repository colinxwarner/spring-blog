package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

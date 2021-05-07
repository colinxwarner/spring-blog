package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    // listen for the GET request for the login page, to display the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "/users/login";
    }
}

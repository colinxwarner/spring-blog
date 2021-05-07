package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.models.Ad;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@Controller
public class UserController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    // why did we not just create a BcryptPasswordEncoder?
    // so that we can leave this file alone, and only change the "security configuration stuff" IN the "SecurityConfiguration" class.
    public UserController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("user/{id}")
    public String profilePage(@PathVariable long id,Model model) {
        model.addAttribute("user", userRepo.getOne(id));
        return "/users/profile";
    }
    // Want the user to be able to access a sign-up page
    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "/users/signup";
    }
    // What happens when a new user submits the register form?
    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword()); // ~plaintext password
        user.setPassword(hash); // immediately no longer have access to the plaintext password. It's hashed
        userRepo.save(user);
        userRepo.save(user);  // you must actually save the user in order to ... USE that user later
        return "redirect:/login";
        // we can also redirect directly to the profile page, which we will do later
    }
}
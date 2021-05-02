package com.codeup.lunablog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String index() {
        return "Returning posts...";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "Showing specific post...";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "Here is a view to create a new post...";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String insert() {
        return "Saving a new post...";
    }

}

package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String posts() {
        return "posts index page";
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String postById(@PathVariable int id) {
        return "viewing an individual post";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPost() {
        return "view the form for creating a post";
    }

    @RequestMapping(value = "posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPostSent() {
        return "create a new post";
    }
}

package com.example.springblog.controllers;

import com.example.springblog.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    List<Post> posts = new ArrayList<>();

    public PostController() {
        posts.add(new Post("Beagles", "my beagles"));
        posts.add(new Post("My jobs", "my jobs"));
        posts.add(new Post("Test", "this is a test"));
    }

    @GetMapping("/posts")
    public String postsIndex(Model model) {
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")

    public String postView(Model model) {
        Post post = new Post("test", "this is a test");
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String postForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "creating a new post";
    }
}

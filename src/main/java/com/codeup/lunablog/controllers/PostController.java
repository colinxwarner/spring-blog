package com.codeup.lunablog.controllers;

import com.codeup.lunablog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String index(Model vModel) {

        List<Post> posts = new ArrayList<>(Arrays.asList(
                new Post("Post Title 1", "Yadda yadda yadda yadda 1."),
                new Post("Post Title 2", "Yadda yadda yadda yadda 2."),
                new Post("Post Title 3", "Yadda yadda yadda yadda 3.")
        ));

        vModel.addAttribute("posts", posts);

        return "posts/index";

    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model vModel) {
        Post post = new Post("Test Title", "Test Body");
        vModel.addAttribute("id", id);
        vModel.addAttribute("post", post);
        return "posts/show";
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

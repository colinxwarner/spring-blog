package com.codeup.lunablog.controllers;

import com.codeup.lunablog.models.Post;
import com.codeup.lunablog.repositories.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepo postsDao;

    public PostController(PostRepo postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String index(Model vModel) {
        vModel.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model vModel) {
        Post post = new Post("Test Title", "Test Body");
        vModel.addAttribute("id", id);
        vModel.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model vModel) {
        Post postToEdit = postsDao.getOne(id);
        vModel.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(
            @PathVariable long id,
            @RequestParam String title,
            @RequestParam String body) {

        Post postToUpdate = new Post(
                id,
                title,
                body
        );

        postsDao.save(postToUpdate);

        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
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

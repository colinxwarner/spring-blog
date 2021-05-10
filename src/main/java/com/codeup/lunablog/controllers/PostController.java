package com.codeup.lunablog.controllers;

import com.codeup.lunablog.models.Post;
import com.codeup.lunablog.models.User;
import com.codeup.lunablog.repositories.PostRepo;
import com.codeup.lunablog.repositories.UserRepo;
import com.codeup.lunablog.services.EmailSvc;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepo postsDao;
    private final UserRepo usersDao;
    private final EmailSvc emailSvc;

    public PostController(PostRepo postsDao, UserRepo usersDao, EmailSvc emailSvc) {
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailSvc = emailSvc;
    }

    @GetMapping("/posts")
    public String index(Model vModel) {
        vModel.addAttribute("posts", postsDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model vModel) {
        vModel.addAttribute("post", postsDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/{id}/edit")
    public String edit(@PathVariable long id, Model vModel) {
        Post postToEdit = postsDao.getOne(id);
        vModel.addAttribute("post", postToEdit);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String update(@ModelAttribute Post post) {
        postsDao.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String delete(@PathVariable long id) {
        postsDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String create(Model vModel) {
        vModel.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        post.setUser(author);
        Post savedPost = postsDao.save(post);
        emailSvc.prepareAndSend(post, "Post Created!", "You have just created a post!");
        return "redirect:/posts/" + savedPost.getId();
    }

}

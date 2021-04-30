package com.example.springblog.controllers;

import com.example.springblog.repository.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdController {
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("ads/{id}")
    public String getOne(@PathVariable long id, Model model) {
        model.addAttribute("ad", adDao.getOne(id));
        System.out.println(model);
        return "ads/show";
    }

}

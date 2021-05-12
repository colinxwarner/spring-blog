package com.example.springblog.controllers;

import com.example.springblog.models.Ad;
import com.example.springblog.models.User;
import com.example.springblog.repositories.AdRepository;
import com.example.springblog.repositories.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "ads/show";
    }

    @GetMapping("/ads/create")
    public String showCreateForm(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String create(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description
    ) {
        Ad ad = new Ad();
        ad.setTitle(title);
        ad.setDescription(description);

        adDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}/edit")
    public String edit(@PathVariable long id, Model model) {
        model.addAttribute("ad", adDao.getOne(id));
        return "ads/edit";
    }

    @PostMapping("/ads/{id}/edit")
    public String update(@PathVariable long id, @ModelAttribute Ad ad) {
        Ad oldAd = adDao.getOne(id);
        oldAd.setTitle(ad.getTitle());
        oldAd.setDescription(ad.getDescription());
        adDao.save(oldAd);
        return "redirect:/ads/" + id;
    }

    @PostMapping("/ads/{id}/delete")
    public String delete(@PathVariable long id) {
        adDao.deleteById(id);
        return "redirect:/ads";
    }
}

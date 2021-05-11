package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.AdCategory;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class AdController {
    private final AdRepository adRepo;
    private final UserRepository userRepo;
    private final EmailService emailService;

    public AdController(AdRepository adRepo, UserRepository userRepo, EmailService emailService) {
        this.adRepo = adRepo;
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

    @RequestMapping(path = "/ads", method = RequestMethod.GET)
    public String showAllAds(Model model) {
        model.addAttribute("ads", adRepo.findAll());
        return "ads/index";
    }

    @RequestMapping(path = "/ads/{id}", method = RequestMethod.GET)
    public String showSingleAd(@PathVariable long id, Model model) {
        User user = userRepo.findAll().get(0);
        Ad ad = adRepo.getOne(id);
        ad.setOwner(user);
        model.addAttribute("ad", adRepo.getOne(id));
        return "ads/show";
    }

    @GetMapping("ads/hardcoded/create")
    public String createHardcodedAd() {
        Ad ad = new Ad();
        ad.setTitle("Title to Chevy Silverado, Title only.");
        ad.setDescription("Selling the title to Daddy's Silverado. His car was lost at sea.");
        ad.setCategories(new ArrayList<AdCategory>());
        ad.setOwner(userRepo.getOne(1L));
        adRepo.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/create")
    public String showCreateView(Model model) {
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
    public String createAd(@ModelAttribute Ad ad) {
        String update;

        // set flag values for a create email
        if (ad.getId() == 0) {
            update = "Created Ad: ";
            ad.setOwner(userRepo.findAll().get(0));
        }
        // set flag values for an edit email
        else {
            update = "Edited Ad: ";
            ad.setOwner(adRepo.getOne(ad.getId()).getOwner());
        }
        adRepo.save(ad);

        // send create or edit notification
        emailService.prepareAndSend(ad.getOwner().getEmail(),
                update + ad.getTitle(),
                ad.getTitle() +"\n\n" + ad.getDescription());
        return "redirect:/ads/" + ad.getId();
    }

    @GetMapping("/ads/delete/{id}")
    public String deleteAd(@PathVariable long id) {
        Ad ad = adRepo.getOne(id);
        ad.setOwner(adRepo.getOne(id).getOwner()); // get owner from database
        adRepo.delete(ad);

        // send delete notification
        emailService.prepareAndSend(ad.getOwner().getEmail(),
                "Deleted Ad: " + ad.getTitle(),
                ad.getTitle() +"\n\n" + ad.getDescription());
        return "redirect:/ads";
    }

    @GetMapping("/ads/edit/{id}")
    public String editAd(@PathVariable long id, Model model) {
        Ad ad = adRepo.getOne(id);
        model.addAttribute("ad", ad);
        return "ads/create";
    }
}

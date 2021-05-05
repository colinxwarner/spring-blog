package com.codeup.lunablog.controllers;

import com.codeup.lunablog.models.Ad;
import com.codeup.lunablog.models.AdDetails;
import com.codeup.lunablog.repositories.AdDetailsRepo;
import com.codeup.lunablog.repositories.AdRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdController {

    // Constructor dependency injection
    private final AdRepo adsDao;
    private final AdDetailsRepo detailsDao;

    public AdController(AdRepo adsDao, AdDetailsRepo detailsDao) {
        this.adsDao = adsDao;
        this.detailsDao = detailsDao;
    }

    @GetMapping("/ads")
    @ResponseBody
    public List<Ad> getAllAds() {
        return adsDao.findAll();
    }

    /*
    How do we delete? Edit? Insert? Show a specific record?
     */

    @GetMapping("/ads/{id}")
    public String getOneAd(@PathVariable long id, Model vModel) {
        // get ad from dao then put in vModel
        Ad ad = adsDao.getOne(id);
        vModel.addAttribute("ad", ad);
        return "ads/show";
    }

    @GetMapping("/ads/delete")
    public String deleteAd() {
        // delete ad
        adsDao.deleteById(4L);
        return "redirect:/ads"; // redirect to "/ads"
    }

    @GetMapping("/ads/create")
    public String createAd() {
        Ad ad = new Ad(
                "Unicorn",
                "It is a very small unicorn",
                2000
        );
        adsDao.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/update")
    public String updateAd() {
       Ad updatedAd = new Ad(
               101,
               "Dragon",
               "It is a dragon.",
               200
       );
       adsDao.save(updatedAd);
       return "redirect:/ads";
    }

    @GetMapping("/ads/details")
    public String details() {

// ================= PERSIST
//        Ad ad = new Ad("New Ad", "New Description", 20);
//        adsDao.save(ad);
//        AdDetails adDetails = new AdDetails("A detail");
//        ad.setAdDetails(adDetails);
//        adsDao.save(ad);

// ================= ACCESS DATA THROUGH RELATIONSHIP
//        System.out.println(adsDao.getOne(1L).getAdDetails().getExtraStr());

// ================= UPDATE DETAIL
//        Ad ad = adsDao.getOne(1L);
//        AdDetails adDetails = ad.getAdDetails();
//        adDetails.setExtraStr("Updated Detail");
//        ad.setAdDetails(adDetails);
//        adsDao.save(ad);


// ================= DELETE DETAIL
//        Ad ad = new Ad(1, "New Ad", "New Description", 20, null);
//        adsDao.save(ad);
//        detailsDao.deleteById(1L);

// ================= CREATE DETAIL AGAIN
//        AdDetails adDetails = new AdDetails("A detail");
//        adsDao.getOne(1L).setAdDetails(adDetails);
//        adsDao.save(adsDao.getOne(1L));

// ================= DELETE AD
//        adsDao.deleteById(1L);

        return "redirect:/ads";
    }



}

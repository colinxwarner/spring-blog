package com.codeup.lunablog.controllers;

import com.codeup.lunablog.models.Ad;
import com.codeup.lunablog.models.AdDetails;
import com.codeup.lunablog.models.AdImage;
import com.codeup.lunablog.repositories.AdDetailsRepo;
import com.codeup.lunablog.repositories.AdRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
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
    /**
     * This is simply a route in the app to test the various CRUD operations on a one-to-one relationship.
     */
    public String details() {

        /*

            DB Schema...

            ads
                id
                title
                description
                price_in_cents
                ad_details_id (foreign key on owning side of relationship)

            ad_details
                id
                extra_str

         */



        /*
            CREATE NEW AD AND DETAILS IN ONE STEP

            An ad and it's ad details can be created with a single save to the DB.
            The following steps accomplish this...
                1. create the ad (minus the primary key) including the AdDetail (minus it's primary key)
                2. save the ad
         */

//        Ad ad = new Ad(
//                "New Ad",
//                "New Description",
//                20,
//                new AdDetails("A detail")
//        );
//        adsDao.save(ad);



         /*
            CREATE NEW AD AND DETAIL IN TWO SEPARATE STEPS

            If an ad is created then details can be added as a separate step for the user.
            (another use case for this would be a user account and user profile details)
            The following steps accomplish this...
                1. the ad can be saved first with the foreign key set to null
                2. a new ad object can then be set on the ad at a later point
         */

//        // step 1 (save the ad)
//        Ad anotherAd = new Ad(
//                "Another New Ad",
//                "Another New Description",
//                40
//        );
//        adsDao.save(anotherAd);
//
//        // step 2 (ad details to the ad then save the ad, creating the details)
//        anotherAd.setAdDetails(new AdDetails("Another Ad's Details"));
//        adsDao.save(anotherAd);



        /*
            ACCESS DATA THROUGH RELATIONSHIP

            In a view, you can now access ad details through an ad, e.g.

                <span th:text="${ad.adDetails.extraStr}"></span>

         */

//        System.out.println(adsDao.getOne(1L).getAdDetails().getExtraStr());



        /*
            UPDATE DETAIL

            The fields of a non-owning-side entity in a one-to-one relationship can be updated through the owning side.
            The following steps will accomplish this...
                1. get the ad with the detail to update
                2. get the the adDetails object from the ad
                3. set the new values for all fields for the adDetails
                4. set the updated adDetails object on the ad
                5. persist the changes in the DB by saving the ad again
         */

//        Ad ad = adsDao.getOne(1L); // 1. get the ad with the detail to update
//        AdDetails adDetails = ad.getAdDetails(); // 2. get the the adDetails object from the ad
//        adDetails.setExtraStr("Updated Detail"); // 3. set the new values for all fields for the adDetails
//        ad.setAdDetails(adDetails); // 4. set the updated adDetails object on the ad
//        adsDao.save(ad); // 5. persist the changes in the DB by saving the ad again



        /*
            DELETE DETAIL

            An adDetail object can be deleted without deleting the owning-side (an ad).
            To delete an ad detail...
                1. set the adDetail to null on the ad
                2. save the ad to persist the disassociation of the ad detail with the ad
                3. the adDetail can then be safely deleted
         */

//        Ad adWithDetailToRemove = adsDao.getOne(2L); // get the ad with the detail to remove
//        adWithDetailToRemove.setAdDetails(null); // 1. set the adDetail to null on the ad
//        adsDao.save(adWithDetailToRemove); // 2. save the ad to persist the disassociation of the ad detail with the ad
//        detailsDao.deleteById(3L); // 3. the adDetail can then be safely deleted



        /*
            CREATE DETAIL AGAIN

            After a detail has been removed, a new detail can be created at a later point.

         */

//        AdDetails adDetails = new AdDetails("A new detail"); // create a new adDetail
//        adsDao.getOne(1L).setAdDetails(adDetails); // set the adDetails on the ad in question
//        adsDao.save(adsDao.getOne(1L)); // persist the change



        /*
            DELETE AN AD

            An ad can be deleted. When the owning side of a one-to-one relationship is deleted, the delete will cascade to the non-owning-side;
            in other words, the adDetails object will be removed when it's owning ad is deleted.

         */

        adsDao.deleteById(1L);

        return "redirect:/ads";
    }


    @GetMapping("/ads/images")
    /**
     * This is simply a route in the app to test the various CRUD operations on a one-to-many relationship.
     */
    public String images() {

        /*
            CREATE AN AD WITH IMAGES IN ONE STEP

            1. create an ad object
            2. set images objects on the ad (it is required that each ad image also includes the ad)

         */

//        Ad ad = new Ad(
//                "Ad Title",
//                "Ad Description",
//                30
//        );
//
//        ad.setAdImages(
//                new ArrayList<>(Arrays.asList(
//                        new AdImage("https://via.placeholder.com/150", ad),
//                        new AdImage("https://via.placeholder.com/300", ad),
//                        new AdImage("https://via.placeholder.com/100", ad)
//                )));
//
//        adsDao.save(ad);



        /*
            ADD AN ADDITIONAL IMAGE TO AN AD

            1. get ad to add image to
            2. get the list of images and add a new image to it
            3. reset the value of the list of images on the ad to the updated bigger list
            4. persist the change in the DB

         */

//        Ad adToAddImage = adsDao.getOne(1L);
//        List<AdImage> images = adToAddImage.getAdImages();
//        images.add(new AdImage("https://via.placeholder.com/400", adToAddImage));
//        adToAddImage.setAdImages(images);
//
//        adsDao.save(adToAddImage);



        /*
            EDIT AN AD IMAGE

            1. get ad to edit image on
            2. get list of images from ad
            3. remove the ad from the list but store in variable
            4. edit the fields of the image
            5. add the edited image to the list of images from the ad
            6. set the list of images back on the ad
            7. persist the change in the DB

         */

//        Ad adToEditImage = adsDao.getOne(1L);
//        List<AdImage> images = adToEditImage.getAdImages();
//        AdImage adImage = images.remove(3);
//        adImage.setUrl("https://via.placeholder.com/500");
//        images.add(adImage);
//        adToEditImage.setAdImages(images);
//        adsDao.save(adToEditImage);


        /*
            EDIT AN AD, KEEPING THE IMAGES INTACT

            1. get the ad to edit
            2. edit the fields
            3. persist the change in the DB

         */

//        Ad adToEdit = adsDao.getOne(1L);
//        adToEdit.setTitle("Edited Title");
//        adsDao.save(adToEdit);


        /*
            REMOVE AN IMAGE

            1. get the ad to remove the image from
            2. get the list of images
            3. remove the image from the list of ad images
            4. set the smaller list of images back on the ad
            5. persist the change (if orphan removal is set to true, there is no need to delete the orphaned image record in the DB)

         */

//        Ad adToRemoveImage = adsDao.getOne(1L);
//        List<AdImage> images = adToRemoveImage.getAdImages();
//        images.remove(1);
//        adToRemoveImage.setAdImages(images);
//        adsDao.save(adToRemoveImage);



        /*
            DELETE THE AD

            Deleting the ad will cascade a delete to remove all associated ad images

         */

//        adsDao.deleteById(1L);

        return "redirect:/ads";
    }


}

package com.codeup.lunablog.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "ads")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int priceInCents;

    /*
        This annotation and field represents the other table in the one-to-one relationship.
        The cascade allows us to save, update and delete adDetails through an ad.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private AdDetails adDetails;


    /*

        ships and captains (ships is the owning side)

        @Entity
        public class Ship {

            @OneToOne(cascade = CascadeType.ALL)
            private Captain captain;

        }

        @Entity
        public class Captain {

            private String name;

        }



        @Entity
        public class Company {

            ???

        }

        @Entity
        public class CEO {

            ???

        }


        ${ship.captain.name}



     */


    public Ad() {
    }

    public Ad(String title, String description, int priceInCents) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
    }

    // added a constructor used when saving an ad with ad details
    public Ad(String title, String description, int priceInCents, AdDetails adDetails) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
        this.adDetails = adDetails;
    }

    public Ad(long id, String title, String description, int priceInCents) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(int priceInCents) {
        this.priceInCents = priceInCents;
    }


    // getter and setter for the adDetails object

    public AdDetails getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(AdDetails adDetails) {
        this.adDetails = adDetails;
    }
}

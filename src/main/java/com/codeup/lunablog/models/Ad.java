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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ad_details_id", referencedColumnName = "id")
    @JsonManagedReference
    private AdDetails adDetails;

    public Ad() {
    }

    public Ad(String title, String description, int priceInCents) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
    }

    public Ad(long id, String title, String description, int priceInCents) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
    }

    public Ad(long id, String title, String description, int priceInCents, AdDetails adDetails) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
        this.adDetails = adDetails;
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

    public AdDetails getAdDetails() {
        return adDetails;
    }

    public void setAdDetails(AdDetails adDetails) {
        this.adDetails = adDetails;
    }
}

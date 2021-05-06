package com.codeup.lunablog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

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
    private AdDetails adDetails;

    /**
     * @OneToMany is on the side that doesn't contain the foreign key
     * - the cascade allows us to CRUD images through ads
     * - the mappedBy prevents an unneeded mapping table to be created by Hibernate
     * - the orphanRemoval will automatically delete any images if they are removed from an ad
     *
     * @JsonManagedReference prevents a circular reference that creates, avoiding an infinite loop of JSON
     * - put on the side without the foreign key
     */
    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "ad",
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<AdImage> adImages;

    /**
     * The @ManyToMany is required on both sides.
     * Only one side can include the @JoinTable specification and the other must contain the mappedBy.
     * The @JoinTable is only needed here to have control over the default mapping table column names created by Hibernate.
     */
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "ads_categories",
            joinColumns = { @JoinColumn(name = "ad_id") },
            inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private List<AdCategory> adCategories;

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

    public Ad(String title, String description, List<AdCategory> adCategories, int priceInCents) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
        this.adCategories = adCategories;
    }

    public Ad(String title, String description, int priceInCents, List<AdImage> adImages) {
        this.title = title;
        this.description = description;
        this.priceInCents = priceInCents;
        this.adImages = adImages;
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

    public List<AdImage> getAdImages() {
        return adImages;
    }

    public void setAdImages(List<AdImage> adImages) {
        this.adImages = adImages;
    }

    public List<AdCategory> getAdCategories() {
        return adCategories;
    }

    public void setAdCategories(List<AdCategory> adCategories) {
        this.adCategories = adCategories;
    }
}

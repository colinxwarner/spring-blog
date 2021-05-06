package com.codeup.lunablog.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ad_categories")
public class AdCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "adCategories")
    private List<Ad> ads;

    public AdCategory() {
    }

    public AdCategory(String name, List<Ad> ads) {
        this.name = name;
        this.ads = ads;
    }

    public AdCategory(long id, String name, List<Ad> ads) {
        this.id = id;
        this.name = name;
        this.ads = ads;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }


}

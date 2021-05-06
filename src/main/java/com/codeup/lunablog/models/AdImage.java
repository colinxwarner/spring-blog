package com.codeup.lunablog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "ad_images")
public class AdImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    /**
     * The @ManyToOne annotation is used on the side with the foreign key
     * The @JsonBackReference is used on the side with the foreign key to prevent a cirular reference
     */
    @ManyToOne
    @JsonBackReference
    private Ad ad;

    public AdImage() {
    }

    public AdImage(String url) {
        this.url = url;
    }

    public AdImage(String url, Ad ad) {
        this.url = url;
        this.ad = ad;
    }

    public AdImage(long id, String url, Ad ad) {
        this.id = id;
        this.url = url;
        this.ad = ad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}

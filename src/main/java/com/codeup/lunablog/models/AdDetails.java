package com.codeup.lunablog.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "ad_details")
public class AdDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String extraStr;

    @OneToOne(mappedBy = "adDetails")
    @JsonBackReference
    private Ad ad;

    public AdDetails() {
    }

    public AdDetails(String extraStr) {
        this.extraStr = extraStr;
    }

    public AdDetails(String extraStr, Ad ad) {
        this.extraStr = extraStr;
        this.ad = ad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExtraStr() {
        return extraStr;
    }

    public void setExtraStr(String extraStr) {
        this.extraStr = extraStr;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "AdDetails{" +
                "id=" + id +
                ", extraStr='" + extraStr + '\'' +
                ", ad=" + ad +
                '}';
    }
}

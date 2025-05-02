package com.covid.covidtracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Region {

    @Id
    private String iso;

    private String name;

    public Region() {
    }

    public Region(String iso, String name) {
        this.iso = iso;
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

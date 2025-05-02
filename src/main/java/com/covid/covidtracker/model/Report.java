package com.covid.covidtracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String region;
    private String province;
    private String date;

    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;

    // Constructor vacío
    public Report() {}

    // Constructor con campos útiles
    public Report(String region, String province, String date, int confirmed, int deaths, int recovered, int active) {
        this.region = region;
        this.province = province;
        this.date = date;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

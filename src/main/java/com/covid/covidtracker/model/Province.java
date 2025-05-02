package com.covid.covidtracker.model;

import jakarta.persistence.*;

@Entity
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iso;
    private String province;

    // ✅ Constructor vacío (requerido por JPA)
    public Province() {}

    // ✅ Constructor personalizado para uso manual
    public Province(String iso, String province) {
        this.iso = iso;
        this.province = province;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

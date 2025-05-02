package com.covid.covidtracker.controller;

import com.covid.covidtracker.model.Province;
import com.covid.covidtracker.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    // 🔹 GET /api/provinces → Todas las provincias
    @GetMapping
    public List<Province> getAllProvinces() {
        return provinceService.getAllProvinces();
    }

    // 🔹 GET /api/provinces/{iso} → Provincias filtradas por código ISO (ej: GTM)
    @GetMapping("/{iso}")
    public List<Province> getProvincesByIso(@PathVariable String iso) {
        return provinceService.getProvincesByIso(iso.trim().toUpperCase());
    }
}

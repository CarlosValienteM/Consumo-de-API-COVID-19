package com.covid.covidtracker.controller;

import com.covid.covidtracker.model.Region;
import com.covid.covidtracker.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping(produces = "application/json")
    public List<Region> getAllRegions() {
        return regionService.getAll();
    }

    @GetMapping("/{iso}")
    public Region getRegionByIso(@PathVariable String iso) {
        System.out.println("📌 Buscando región con ISO recibido: [" + iso + "]"); // Debug
        Region region = regionService.getByIso(iso);
        if (region != null) {
            System.out.println("📡 Región encontrada: " + region.getIso() + " - " + region.getName());
        } else {
            System.out.println("⚠️ No se encontró ninguna región con ISO: " + iso);
        }
        return region;
    }
}

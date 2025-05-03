package com.covid.covidtracker.service;

import com.covid.covidtracker.model.Region;
import com.covid.covidtracker.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public void saveAll(List<Region> regions) {
        regionRepository.saveAll(regions);
    }

    public List<Region> getAll() {
        return regionRepository.findAll();
    }
    
    public Region getByIso(String iso) {
    return regionRepository.findById(iso).orElse(null);
    
   }

}

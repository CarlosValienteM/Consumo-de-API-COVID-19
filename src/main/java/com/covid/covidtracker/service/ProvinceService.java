package com.covid.covidtracker.service;

import com.covid.covidtracker.model.Province;
import com.covid.covidtracker.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    // Método para guardar una lista de provincias
    public void saveAll(List<Province> provinces) {
        provinceRepository.saveAll(provinces);
    }

    // Método para obtener todas las provincias
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    // Método para obtener provincias por código ISO
    public List<Province> getProvincesByIso(String iso) {
        return provinceRepository.findByIso(iso);
    }
}

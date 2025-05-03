package com.covid.covidtracker.repository;

import com.covid.covidtracker.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
    List<Province> findByIso(String iso); // ✅ Método personalizado
}

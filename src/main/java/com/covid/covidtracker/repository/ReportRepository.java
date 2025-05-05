package com.covid.covidtracker.repository;

import com.covid.covidtracker.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByProvinceIgnoreCase(String province);

    List<Report> findByRegionIgnoreCase(String region);

    List<Report> findByDate(String date);

    // ✅ Nuevo método para buscar por fecha e ISO
    List<Report> findByDateAndRegionIgnoreCase(String date, String region);
}

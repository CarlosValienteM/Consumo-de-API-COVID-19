package com.covid.covidtracker.repository;

import com.covid.covidtracker.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    // Aquí puedes agregar métodos personalizados si lo necesitas
}

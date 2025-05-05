package com.covid.covidtracker.repository;

import com.covid.covidtracker.model.ExecutedReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExecutedReportRepository extends JpaRepository<ExecutedReport, Long> {
    Optional<ExecutedReport> findByExecutionDateAndCountryIso(String executionDate, String countryIso);
}

package com.covid.covidtracker.service;

import com.covid.covidtracker.model.ExecutedReport;
import com.covid.covidtracker.repository.ExecutedReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExecutedReportService {

    @Autowired
    private ExecutedReportRepository executedReportRepository;

    // Verifica si ya se ejecutó el reporte para ese día y país
    public boolean alreadyExecuted(String date, String iso) {
        Optional<ExecutedReport> existing = executedReportRepository.findByExecutionDateAndCountryIso(date, iso);
        return existing.isPresent();
    }

    // Guarda una nueva ejecución
    public void saveExecution(String date, String iso) {
        ExecutedReport executedReport = new ExecutedReport(date, iso);
        executedReportRepository.save(executedReport);
        System.out.println("🧠 Registro de ejecución guardado para " + iso + " en " + date);
    }
}

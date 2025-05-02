package com.covid.covidtracker.service;

import com.covid.covidtracker.model.Report;
import com.covid.covidtracker.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void saveAll(List<Report> reports) {
        reportRepository.saveAll(reports);
        System.out.println("✅ " + reports.size() + " reportes guardados en la base de datos.");
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }
}

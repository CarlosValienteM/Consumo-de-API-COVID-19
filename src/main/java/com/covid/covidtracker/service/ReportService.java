package com.covid.covidtracker.service;

import com.covid.covidtracker.model.Report;
import com.covid.covidtracker.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<Report> getReportsByProvince(String province) {
        return reportRepository.findByProvinceIgnoreCase(province);
    }

    public List<Report> getReportsByRegion(String region) {
        return reportRepository.findByRegionIgnoreCase(region);
    }

    public List<Report> getReportsByDate(String date) {
        return reportRepository.findByDate(date);
    }

    // ✅ Nueva versión agrupada por provincia y filtrada por fecha + ISO flexible
    public Map<String, List<Report>> getGroupedReportsByProvince(String date, String iso) {
        List<Report> reports = reportRepository.findByDate(date);

        TreeMap<String, List<Report>> grouped = new TreeMap<>();

        for (Report report : reports) {
            String regionCode = report.getRegion();
            if (regionCode != null && regionCode.toUpperCase().startsWith(iso.toUpperCase())) {
                grouped.computeIfAbsent(report.getProvince(), k -> new ArrayList<>()).add(report);
            }
        }

        return grouped;
    }
}

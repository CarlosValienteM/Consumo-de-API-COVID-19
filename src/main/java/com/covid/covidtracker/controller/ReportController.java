package com.covid.covidtracker.controller;

import com.covid.covidtracker.model.Report;
import com.covid.covidtracker.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Obtener todos los reportes
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // Obtener reportes por provincia
    @GetMapping("/by-province")
    public List<Report> getReportsByProvince(@RequestParam String name) {
        return reportService.getReportsByProvince(name);
    }

    // Obtener reportes por región
    @GetMapping("/by-region")
    public List<Report> getReportsByRegion(@RequestParam String name) {
        return reportService.getReportsByRegion(name);
    }

    // Obtener reportes por fecha
    @GetMapping("/by-date")
    public List<Report> getReportsByDate(@RequestParam String date) {
        return reportService.getReportsByDate(date);
    }

    // NUEVO: Obtener reportes agrupados por provincia para un ISO y fecha
    @GetMapping("/grouped-by-province")
    public Map<String, List<Report>> getGroupedByProvince(
            @RequestParam String date,
            @RequestParam String iso) {
        return reportService.getGroupedReportsByProvince(date, iso);
    }
}

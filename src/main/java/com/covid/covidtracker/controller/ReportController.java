package com.covid.covidtracker.controller;

import com.covid.covidtracker.model.Report;
import com.covid.covidtracker.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Endpoint para obtener todos los reportes almacenados
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // (Opcional) Puedes agregar filtros por provincia o región si lo deseas después
}

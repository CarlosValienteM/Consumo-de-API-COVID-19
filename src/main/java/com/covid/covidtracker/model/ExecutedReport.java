package com.covid.covidtracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "executed_reports", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"executionDate", "countryIso"})
})
public class ExecutedReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String executionDate;
    private String countryIso;

    public ExecutedReport() {}

    public ExecutedReport(String executionDate, String countryIso) {
        this.executionDate = executionDate;
        this.countryIso = countryIso;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getCountryIso() {
        return countryIso;
    }

    public void setCountryIso(String countryIso) {
        this.countryIso = countryIso;
    }
}

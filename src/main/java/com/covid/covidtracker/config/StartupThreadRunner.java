package com.covid.covidtracker.config;

import com.covid.covidtracker.model.Province;
import com.covid.covidtracker.model.Region;
import com.covid.covidtracker.model.Report;
import com.covid.covidtracker.service.ProvinceService;
import com.covid.covidtracker.service.RegionService;
import com.covid.covidtracker.service.ReportService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class StartupThreadRunner {

    @Value("${rapidapi.key}")
    private String rapidApiKey;

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void runAfterStartup() {
        new Thread(() -> {
            try {
                System.out.println("⏳ Esperando 15000 milisegundos...");
                Thread.sleep(15000);

                getRegionsFromApi();
                getProvincesFromApi();
                getReportsFromApi();

            } catch (InterruptedException e) {
                System.err.println("❌ Error en el hilo de inicio: " + e.getMessage());
            }
        }).start();
    }

    private void getRegionsFromApi() {
        try {
            System.out.println("📡 Solicitando regiones...");

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", rapidApiKey);
            headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://covid-19-statistics.p.rapidapi.com/regions",
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("✅ Respuesta real de la API de regiones recibida.");

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode data = root.path("data");

                List<Region> regionList = new ArrayList<>();

                for (JsonNode regionNode : data) {
                    String iso = regionNode.path("iso").asText();
                    String name = regionNode.path("name").asText();

                    if (!iso.isBlank() && !name.isBlank()) {
                        Region region = new Region(iso, name);
                        regionList.add(region);
                    }
                }

                regionService.saveAll(regionList);
                System.out.println("✅ " + regionList.size() + " regiones guardadas en la base de datos.");
            } else {
                System.err.println("❌ Error al obtener regiones: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("❌ Error al procesar la respuesta de regiones:");
            e.printStackTrace();
        }
    }

    private void getProvincesFromApi() {
        try {
            System.out.println("📡 Solicitando provincias de Guatemala (iso = GTM)...");

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", rapidApiKey);
            headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://covid-19-statistics.p.rapidapi.com/provinces?iso=GTM",
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                System.out.println("✅ Respuesta real de la API de provincias recibida.");

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode data = root.path("data");

                List<Province> provinceList = new ArrayList<>();

                for (JsonNode provinceNode : data) {
                    String iso = provinceNode.path("iso").asText();
                    String provinceName = provinceNode.path("province").asText();

                    if (!provinceName.isBlank()) {
                        Province province = new Province(iso, provinceName);
                        provinceList.add(province);
                    }
                }

                provinceService.saveAll(provinceList);
                System.out.println("✅ " + provinceList.size() + " provincias guardadas en la base de datos.");
            } else {
                System.err.println("❌ Error al obtener provincias: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("❌ Ocurrió un error al obtener provincias:");
            e.printStackTrace();
        }
    }

    private void getReportsFromApi() {
        try {
            System.out.println("📡 Solicitando reportes de GTM para 2022-04-16...");

            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", rapidApiKey);
            headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            String url = "https://covid-19-statistics.p.rapidapi.com/reports?iso=GTM&date=2022-04-16";

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode root = objectMapper.readTree(response.getBody());
                JsonNode data = root.path("data");

                List<Report> reportList = new ArrayList<>();

                for (JsonNode reportNode : data) {
                    String region = reportNode.path("region").path("name").asText();
                    String province = reportNode.path("region").path("province").asText();
                    String date = reportNode.path("date").asText();
                    int confirmed = reportNode.path("confirmed").asInt();
                    int deaths = reportNode.path("deaths").asInt();
                    int recovered = reportNode.path("recovered").asInt();
                    int active = reportNode.path("active").asInt();

                    Report report = new Report(region, province, date, confirmed, deaths, recovered, active);
                    reportList.add(report);
                }

                reportService.saveAll(reportList);
                System.out.println("✅ " + reportList.size() + " reportes guardados en la base de datos.");
            } else {
                System.err.println("❌ Error al obtener reportes: " + response.getStatusCode());
            }

        } catch (Exception e) {
            System.err.println("❌ Ocurrió un error al obtener reportes:");
            e.printStackTrace();
        }
    }
}

package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClimateController {

    private final ClimateDataService climateDataService;

    public ClimateController(ClimateDataService climateDataService) {
        this.climateDataService = climateDataService;
    }

    @GetMapping("/climate")
    public String showClimatePage(Model model) {
        // Cargar datos iniciales desde DynamoDB
        List<Map<String, Object>> climateData = climateDataService.getAllClimateData();
        model.addAttribute("climateData", climateData);
        return "climate";
    }
}

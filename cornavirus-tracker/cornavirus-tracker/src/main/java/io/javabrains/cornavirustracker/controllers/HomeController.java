package io.javabrains.cornavirustracker.controllers;

import io.javabrains.cornavirustracker.models.LocationStats;
import io.javabrains.cornavirustracker.services.CornaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CornaVirusDataService cornaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = cornaVirusDataService.getAllStats();
        int TotalCases = allStats.stream().mapToInt(stat -> stat.getLatestReportedCases()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", TotalCases);
        return "home";
    }
}

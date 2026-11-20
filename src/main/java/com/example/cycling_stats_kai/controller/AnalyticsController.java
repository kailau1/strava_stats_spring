package com.example.cycling_stats_kai.controller;

import com.example.cycling_stats_kai.model.AnalyticsPeriod;
import com.example.cycling_stats_kai.model.DTOs.OverallStatsResponse;
import com.example.cycling_stats_kai.services.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/overall")
    public OverallStatsResponse getOverallStats(
            @RequestParam(required = false) AnalyticsPeriod period,
            @RequestParam(required = false) Instant start,
            @RequestParam(required = false) Instant end
    ) {

        return this.analyticsService.getOverallStats(period, start, end);
    }
}

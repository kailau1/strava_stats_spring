package com.example.cycling_stats_kai.services;

import com.example.cycling_stats_kai.model.DTOs.OverallStatsResponse;
import com.example.cycling_stats_kai.repositories.StravaActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final StravaActivityRepository stravaActivityRepository;

    public AnalyticsService(StravaActivityRepository stravaActivityRepository) {
        this.stravaActivityRepository = stravaActivityRepository;
    }

    public OverallStatsResponse getOverallStats(){
        return new OverallStatsResponse(
                this.stravaActivityRepository.findTotalDistance(),
                this.stravaActivityRepository.findTotalMovingTime(),
                this.stravaActivityRepository.findTotalRides(),
                this.stravaActivityRepository.findTotalAverageSpeed(),
                this.stravaActivityRepository.findMaxSpeed()
                );
    }
}

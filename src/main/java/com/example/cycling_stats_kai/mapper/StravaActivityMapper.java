package com.example.cycling_stats_kai.mapper;

import com.example.cycling_stats_kai.model.DTOs.StravaActivityDTO;
import com.example.cycling_stats_kai.model.StravaActivity;
import org.springframework.stereotype.Component;

@Component
 public class StravaActivityMapper {

    public StravaActivity toEntity(StravaActivityDTO stravaActivityDTO) {
        StravaActivity activity = new StravaActivity();

        activity.setAverageSpeed(stravaActivityDTO.averageSpeed() * 3.6); // km/h
        activity.setDistance(stravaActivityDTO.distance() / 1000); // km
        activity.setAverageWatts(stravaActivityDTO.averageWatts()); // W
        activity.setMaxSpeed(stravaActivityDTO.maxSpeed() * 3.6); // km/h
        activity.setTotalElevationGain(stravaActivityDTO.totalElevationGain()); // meters
        activity.setMovingTime(stravaActivityDTO.movingTime()); // Left in seconds so multiple calculations can be done in future.
        activity.setStartDate(stravaActivityDTO.startDate()); // Instant
        activity.setId(stravaActivityDTO.id());
        activity.setSportType(stravaActivityDTO.sportType());

        return activity;
    }
}

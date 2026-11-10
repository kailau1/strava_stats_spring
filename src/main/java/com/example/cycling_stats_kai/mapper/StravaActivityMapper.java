package com.example.cycling_stats_kai.mapper;

import com.example.cycling_stats_kai.model.DTOs.StravaActivityDTO;
import com.example.cycling_stats_kai.model.StravaActivity;
import org.springframework.stereotype.Component;

@Component
 public class StravaActivityMapper {

    public StravaActivity toEntity(StravaActivityDTO stravaActivityDTO) {
        StravaActivity activity = new StravaActivity();

        activity.setAverageSpeed(stravaActivityDTO.getAverageSpeed() * 3.6); // km/h
        activity.setDistance(stravaActivityDTO.getDistance() / 1000); // km
        activity.setAverageWatts(stravaActivityDTO.getAverageWatts()); // W
        activity.setMaxSpeed(stravaActivityDTO.getMaxSpeed() * 3.6); // km/h
        activity.setTotalElevationGain(stravaActivityDTO.getTotalElevationGain()); // meters
        activity.setMovingTime(stravaActivityDTO.getMovingTime()); // Left in seconds so multiple calculations can be done in future.
        activity.setStartDate(stravaActivityDTO.getStartDate()); // Instant
        activity.setId(stravaActivityDTO.getId());
        activity.setSportType(stravaActivityDTO.getSportType());

        return activity;
    }
}

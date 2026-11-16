package com.example.cycling_stats_kai.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record StravaActivityDTO(

    long id,
    double distance,
    @JsonProperty("moving_time") Integer movingTime,
    @JsonProperty("total_elevation_gain") double totalElevationGain,
    @JsonProperty("average_speed") double averageSpeed,
    @JsonProperty("max_speed") double maxSpeed,
    @JsonProperty("average_watts") Double averageWatts,
    @JsonProperty("start_date") Instant startDate,
    @JsonProperty("sport_type") String sportType

    )
{}


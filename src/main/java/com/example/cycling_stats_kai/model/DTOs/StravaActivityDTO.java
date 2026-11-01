package com.example.cycling_stats_kai.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class StravaActivityDTO {

    private double distance;

    @JsonProperty("moving_time")
    private double movingTime;

    @JsonProperty("total_elevation_gain")
    private double totalElevationGain;

    @JsonProperty("average_speed")
    private double averageSpeed;

    @JsonProperty("max_speed")
    private double maxSpeed;

    @JsonProperty("average_watts")
    private Double averageWatts;

    @JsonProperty("start_date")
    private Instant startDate;

}


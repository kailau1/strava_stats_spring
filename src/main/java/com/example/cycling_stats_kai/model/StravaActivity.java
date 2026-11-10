package com.example.cycling_stats_kai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class StravaActivity {

    @Id
    private long id;

    private double distance;

    private Integer movingTime;

    private double totalElevationGain;

    private double averageSpeed;

    private double maxSpeed;

    private Double averageWatts;

    private Instant startDate;

    private String sportType;
}

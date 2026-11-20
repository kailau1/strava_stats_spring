package com.example.cycling_stats_kai.repositories;

import com.example.cycling_stats_kai.model.DTOs.OverallStatsResponse;
import com.example.cycling_stats_kai.model.StravaActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface StravaActivityRepository extends JpaRepository <StravaActivity, Long>{

    @Query("SELECT MAX(a.startDate) FROM StravaActivity a")
    Optional<Instant> findLatestActivityDate();

    @Query("""
        SELECT 
            new com.example.cycling_stats_kai.model.DTOs.OverallStatsResponse(
                COALESCE(SUM(distance), 0),
                COALESCE(SUM(movingTime), 0),
                COUNT(*),
                SUM(a.distance) / SUM(a.movingTime) * 3600,
                COALESCE(MAX(a.maxSpeed), 0)
        )
        FROM StravaActivity a
        WHERE a.sportType = 'Ride'
        """)
    OverallStatsResponse findOverallStats();
}

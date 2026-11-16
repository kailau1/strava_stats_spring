package com.example.cycling_stats_kai.repositories;

import com.example.cycling_stats_kai.model.StravaActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface StravaActivityRepository extends JpaRepository <StravaActivity, Long>{

    @Query("SELECT MAX(a.startDate) FROM StravaActivity a")
    Optional<Instant> findLatestActivityDate();

    @Query("""
            SELECT COALESCE(SUM(distance), 0) 
            FROM StravaActivity a
            WHERE a.sportType = 'Ride'
            """)
    Double findTotalDistance();

    @Query("""
            SELECT SUM(a.distance) / SUM(a.movingTime) * 3600
            FROM StravaActivity a
            WHERE a.sportType = 'Ride'
            """)
    Double findTotalAverageSpeed();

    @Query("""
            SELECT COALESCE(SUM(movingTime), 0)
            FROM StravaActivity a
            WHERE a.sportType = 'Ride'
            """)
    Long findTotalMovingTime();

    @Query("""
            SELECT COALESCE(MAX(a.maxSpeed), 0)
            FROM StravaActivity a
            WHERE a.sportType = 'Ride'
            """)
    Double findMaxSpeed();

    @Query("""
            SELECT COUNT(*)
            FROM StravaActivity a
            WHERE a.sportType = 'Ride'
            """)
    Long findTotalRides();
}

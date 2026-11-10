package com.example.cycling_stats_kai.repositories;

import com.example.cycling_stats_kai.model.StravaActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Optional;

public interface StravaActivityRepository extends JpaRepository <StravaActivity, Long>{

    @Query("SELECT MAX(a.startDate) FROM StravaActivity a")
    Optional<Instant> findLatestActivityDate();
}

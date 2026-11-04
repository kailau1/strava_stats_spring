package com.example.cycling_stats_kai.repositories;

import com.example.cycling_stats_kai.model.StravaActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StravaActivityRepository extends JpaRepository <StravaActivity, Long>{
}

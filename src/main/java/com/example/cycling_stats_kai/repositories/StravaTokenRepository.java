package com.example.cycling_stats_kai.repositories;

import com.example.cycling_stats_kai.model.StravaToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StravaTokenRepository extends JpaRepository<StravaToken, Long>{
}

package com.example.cycling_stats_kai.model.DTOs;

public record OverallStatsResponse(
        Double totalDistanceKm,
        Long totalMovingTimeSeconds,
        Long rideCount,
        Double averageSpeedKmh,
        Double maxSpeedKmh
) {}

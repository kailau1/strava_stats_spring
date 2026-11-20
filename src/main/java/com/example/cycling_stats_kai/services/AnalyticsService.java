package com.example.cycling_stats_kai.services;

import com.example.cycling_stats_kai.model.AnalyticsPeriod;
import com.example.cycling_stats_kai.model.DTOs.OverallStatsResponse;
import com.example.cycling_stats_kai.repositories.StravaActivityRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@Service
public class AnalyticsService {

    private final StravaActivityRepository stravaActivityRepository;

    public AnalyticsService(StravaActivityRepository stravaActivityRepository) {
        this.stravaActivityRepository = stravaActivityRepository;
    }

    public OverallStatsResponse getOverallStats(
            AnalyticsPeriod period,
            Instant start,
            Instant end) {

        if (start != null || end != null) {

            if (start == null || end == null) {
                throw new IllegalArgumentException(
                        "Both start and end dates must be provided"
                );
            }

            if (!start.isBefore(end)) {
                throw new IllegalArgumentException(
                        "Start date must be before end date"
                );
            }

            if (period != null) {
                throw new IllegalArgumentException(
                        "Cannot provide both a period and a custom date range"
                );
            }

            return stravaActivityRepository.findOverallStatsBetween(start, end);
        }

        if (period == null) {
            period = AnalyticsPeriod.ALL_TIME;
        }

        if (period == AnalyticsPeriod.ALL_TIME) {
            return stravaActivityRepository.findOverallStatsAllTime();
        }

        Instant periodStart = calculateStartDate(period);

        return stravaActivityRepository.findOverallStatsSince(periodStart);
    }

    private Instant calculateStartDate(AnalyticsPeriod period) {
        Instant start = switch (period) {
            case LAST_7_DAYS -> Instant.now().minus(7, ChronoUnit.DAYS);
            case LAST_30_DAYS -> Instant.now().minus(30, ChronoUnit.DAYS);
            case YTD -> LocalDate.now()
                    .withDayOfYear(1)
                    .atStartOfDay(ZoneId.of("Europe/London"))
                    .toInstant();
            case ALL_TIME -> null;
        };

        return start;
    }
}

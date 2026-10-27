package com.example.cycling_stats_kai.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "strava")
public record StravaProperties(String baseUrl, String clientId,
                               String clientSecret, String refreshToken) {
}

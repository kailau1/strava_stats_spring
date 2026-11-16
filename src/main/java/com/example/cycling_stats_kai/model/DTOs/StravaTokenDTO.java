package com.example.cycling_stats_kai.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StravaTokenDTO(
        @JsonProperty("access_token") String accessToken,
        @JsonProperty("expires_at") long expiresAt,
        @JsonProperty("expires_in") Integer expiresIn,
        @JsonProperty("refresh_token") String refreshToken
) {}
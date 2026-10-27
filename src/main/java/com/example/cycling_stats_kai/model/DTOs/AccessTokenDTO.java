package com.example.cycling_stats_kai.model.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccessTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_at")
    private long expiresAt;

    @JsonProperty("expires_in")
    private Integer expiresIn;

    @JsonProperty("refresh_token")
    private String refreshToken;
}

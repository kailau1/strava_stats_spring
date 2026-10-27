package com.example.cycling_stats_kai.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class StravaToken {

    @Id
    private long id;

    private String accessToken;

    private Instant expiresAt;

    private String refreshToken;


    public StravaToken(long id, String accessToken, Instant expiresAt, String refreshToken) {
        this.id = id;
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
        this.refreshToken = refreshToken;
    }
}

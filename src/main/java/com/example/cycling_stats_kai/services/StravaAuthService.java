package com.example.cycling_stats_kai.services;

import com.example.cycling_stats_kai.config.StravaProperties;
import com.example.cycling_stats_kai.model.DTOs.StravaTokenDTO;
import com.example.cycling_stats_kai.model.StravaToken;
import com.example.cycling_stats_kai.repositories.StravaTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;


@Service
@Slf4j
public class StravaAuthService {

    private final RestClient stravaAuthClient;
    private final StravaProperties props;
    private final StravaTokenRepository stravaTokenRepository;
    private final Clock clock;
    private static final long TOKEN_ID = 1L;

    public StravaAuthService(RestClient stravaAuthClient, StravaProperties props,
                             StravaTokenRepository stravaTokenRepository, Clock clock) {
        this.stravaAuthClient = stravaAuthClient;
        this.props = props;
        this.stravaTokenRepository = stravaTokenRepository;
        this.clock = clock;
    }

    public String getAccessToken() {

        Optional<StravaToken> saved = stravaTokenRepository.findById(TOKEN_ID);

        if (saved.isPresent() &&
                saved.get().getExpiresAt().isAfter(Instant.now(clock).plusSeconds(60))){
                return saved.get().getAccessToken();
        }

        String refreshToken = saved.map(StravaToken::getRefreshToken).orElse(props.refreshToken());

        return refresh(refreshToken).getAccessToken();

    }

    public StravaTokenDTO refresh(String refreshToken){

        log.info("Refreshing Strava access token");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("client_id", props.clientId());
        body.add("client_secret", props.clientSecret());
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", refreshToken);

        StravaTokenDTO response = this.stravaAuthClient.post()
                .uri("/oauth/token")
                .body(body)
                .retrieve()
                .body(StravaTokenDTO.class);

        StravaToken newToken = new StravaToken(TOKEN_ID, response.getAccessToken(), Instant.ofEpochSecond(response.getExpiresAt()),
                response.getRefreshToken());

        stravaTokenRepository.save(newToken);

        return response;

    }
}

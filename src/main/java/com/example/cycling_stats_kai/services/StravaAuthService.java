package com.example.cycling_stats_kai.services;

import com.example.cycling_stats_kai.config.StravaProperties;
import com.example.cycling_stats_kai.model.DTOs.AccessTokenDTO;
import jakarta.persistence.Access;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;


@Service
public class StravaAuthService {

    private final RestClient stravaAuthClient;
    private final StravaProperties props;
    private final PropertyUpdaterService propertyUpdaterService;

    public StravaAuthService(RestClient stravaAuthClient, StravaProperties props, PropertyUpdaterService propertyUpdaterService) {
        this.stravaAuthClient = stravaAuthClient;
        this.props = props;
        this.propertyUpdaterService = propertyUpdaterService;
    }

    public AccessTokenDTO getAccessToken() {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("client_id", props.clientId());
        body.add("client_secret", props.clientSecret());
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", props.refreshToken());

        AccessTokenDTO response = stravaAuthClient.post()
                .uri("/oauth/token")
                .body(body)
                .retrieve()
                .body(AccessTokenDTO.class);

        if (response.getRefreshToken() != props.refreshToken()){
            this.propertyUpdaterService.updateProperty("refreshToken", response.getRefreshToken());
        }

        return response;

    }
}

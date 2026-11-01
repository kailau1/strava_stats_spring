package com.example.cycling_stats_kai.services;

import com.example.cycling_stats_kai.model.DTOs.StravaActivityDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;


@Service
@Slf4j
public class StravaActivitiesService {

    private final RestClient stravaApiClient;


    public StravaActivitiesService(@Qualifier("stravaApiClient") RestClient stravaApiClient) {
        this.stravaApiClient = stravaApiClient;
    }

    public List<StravaActivityDTO> getActivities(){

        log.info("Attempting to fetch activities.");

        List<StravaActivityDTO> response = this.stravaApiClient.get()
                .uri("/athlete/activities")
                .retrieve()
                .body(new ParameterizedTypeReference<List<StravaActivityDTO>>() {
                });

        return response;
    }
}

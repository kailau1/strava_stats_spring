package com.example.cycling_stats_kai.services;

import com.example.cycling_stats_kai.mapper.StravaActivityMapper;
import com.example.cycling_stats_kai.model.DTOs.StravaActivityDTO;
import com.example.cycling_stats_kai.model.StravaActivity;
import com.example.cycling_stats_kai.repositories.StravaActivityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@Slf4j
public class StravaActivitiesService {

    private final RestClient stravaApiClient;
    private final StravaActivityMapper stravaActivityMapper;
    private final StravaActivityRepository stravaActivityRepository;


    public StravaActivitiesService(@Qualifier("stravaApiClient") RestClient stravaApiClient, StravaActivityMapper stravaActivityMapper, StravaActivityRepository stravaActivityRepository) {
        this.stravaApiClient = stravaApiClient;
        this.stravaActivityMapper = stravaActivityMapper;
        this.stravaActivityRepository = stravaActivityRepository;
    }

    public List<StravaActivityDTO> fetchAndSaveLatestActivities(){

        log.info("Attempting to fetch activities.");

        Optional<Instant> latestDate = this.stravaActivityRepository.findLatestActivityDate();

        long epochSeconds = latestDate
                .map(Instant::getEpochSecond)
                .orElse(0L);

        log.info("Start date Fetched:" + latestDate);

        List<StravaActivityDTO> response = this.stravaApiClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/athlete/activities")
                        .queryParam("after", epochSeconds)
                        .queryParam("per_page", 100)
                        .queryParam("sport_type", "cycling")
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<StravaActivityDTO>>() {
                });

        log.info("Attempting to save activites.");

        List<StravaActivity> activitiesToSave = new ArrayList<>();

        if (response != null) {
            for (StravaActivityDTO activity : response) {
                StravaActivity activityEntity = this.stravaActivityMapper.toEntity(activity);
                activitiesToSave.add(activityEntity);
            }
        }

        this.stravaActivityRepository.saveAll(activitiesToSave);

        return response;
    }

}

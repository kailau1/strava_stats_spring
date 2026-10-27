package com.example.cycling_stats_kai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
@Slf4j
public class StravaAuthClientConfig {

    @Bean
    RestClient stravaAuthClient(StravaProperties props){
        return RestClient.builder()
                .baseUrl(props.baseUrl())
                .build();
    }

}

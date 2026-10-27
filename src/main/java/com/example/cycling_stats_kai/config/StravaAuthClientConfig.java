package com.example.cycling_stats_kai.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


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

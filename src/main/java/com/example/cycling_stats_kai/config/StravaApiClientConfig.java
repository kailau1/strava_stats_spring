package com.example.cycling_stats_kai.config;

import com.example.cycling_stats_kai.services.StravaAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

@Configuration
@Slf4j
public class StravaApiClientConfig {

    @Bean
    public RestClient stravaApiClient(StravaProperties props, StravaAuthService authService) {
        return RestClient.builder()
                .baseUrl(props.baseUrl())
                .requestInterceptor(((request, body, execution) -> {
                    request.getHeaders().setBearerAuth(authService.getAccessToken());
                    return execution.execute(request, body);
                }))
                .requestInterceptor((request, body, execution) -> {
                    log.debug("{} {}", request.getMethod(), request.getURI());
                    request.getHeaders().forEach((name, values) ->
                            log.debug("  {}: {}", name,
                                    name.equalsIgnoreCase(HttpHeaders.AUTHORIZATION) ? "[redacted]" : values));

                    ClientHttpResponse response = execution.execute(request, body);
                    log.debug("Response {}", response.getStatusCode());
                    return response;
                })
                .build();
    }
}

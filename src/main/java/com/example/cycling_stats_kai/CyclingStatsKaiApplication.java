package com.example.cycling_stats_kai;

import com.example.cycling_stats_kai.config.StravaProperties;
import com.example.cycling_stats_kai.services.StravaAuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

import java.time.Instant;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CyclingStatsKaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyclingStatsKaiApplication.class, args);
	}


	@Bean
	CommandLineRunner checkStravaAuth(StravaAuthService authService) {
		return args -> {
			var token = authService.getAccessToken();
			System.out.println("Got Strava token, expires in: %s, expires at: %s".formatted(token.getExpiresIn(), Instant.ofEpochSecond(token.getExpiresAt())));
		};
	}
}


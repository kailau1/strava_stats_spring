package com.example.cycling_stats_kai;

import com.example.cycling_stats_kai.services.StravaActivitiesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CyclingStatsKaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CyclingStatsKaiApplication.class, args);
	}


	@Bean
	@Profile("dev")
	CommandLineRunner fetchActivitiesOnStartup(StravaActivitiesService stravaActivitiesService) {
		return args -> stravaActivitiesService.getActivities();
	}
}


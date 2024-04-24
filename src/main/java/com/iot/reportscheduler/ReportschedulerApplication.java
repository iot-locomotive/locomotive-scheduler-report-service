package com.iot.reportscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class ReportschedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReportschedulerApplication.class, args);
	}

}

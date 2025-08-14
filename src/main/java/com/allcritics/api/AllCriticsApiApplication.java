package com.allcritics.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AllCriticsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AllCriticsApiApplication.class, args);
	}

}

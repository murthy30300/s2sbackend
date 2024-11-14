package com.klu.ss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.klu.ss")
@EntityScan("com.klu.ss.model")
public class SlackToSurplusApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlackToSurplusApplication.class, args);
		System.out.println("hello");
	}

}

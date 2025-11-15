package com.example.Kuber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KuberApplication {

	public static void main(String[] args) {
		SpringApplication.run(KuberApplication.class, args);
	}

}

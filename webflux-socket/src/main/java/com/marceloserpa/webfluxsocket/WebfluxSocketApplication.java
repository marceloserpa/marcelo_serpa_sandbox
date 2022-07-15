package com.marceloserpa.webfluxsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@EnableScheduling
public class WebfluxSocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxSocketApplication.class, args);
	}

}

package com.marceloserpa.springvault;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatabaseConfiguration.class)
public class Application implements CommandLineRunner 	{

	private final DatabaseConfiguration configuration;

	public Application(DatabaseConfiguration configuration) {
		this.configuration = configuration;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	    Logger logger = LoggerFactory.getLogger(Application.class);

	    logger.info("----------------------------------------");
	    logger.info("Configuration properties");
	    logger.info("   db.username is {}", configuration.getUsername());
	    logger.info("   db.password is {}", configuration.getPassword());
	    logger.info("----------------------------------------");		
	}

}

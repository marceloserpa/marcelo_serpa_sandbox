package com.marceloserpa.feignhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.metrics.servo.ServoMetricReader;
import org.springframework.cloud.netflix.metrics.servo.ServoMetricServices;
import org.springframework.cloud.netflix.metrics.servo.ServoMetricsAutoConfiguration;

@EnableAutoConfiguration(exclude={ServoMetricsAutoConfiguration.class} )
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableHystrix
@SpringBootApplication(exclude={ServoMetricServices.class, ServoMetricReader.class})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}

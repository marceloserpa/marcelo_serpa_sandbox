package com.marceloserpa.hystrixfun;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
@EnableHystrix
public class ExternalApiClient {
		
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    },fallbackMethod="fallbackMethodMyOperation")
	public String myOperation(Long seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "processing in " + seconds + " seconds.";
		
	}
	
	public String fallbackMethodMyOperation(Long seconds){
		return "activated circuite breaker.";
	}
	
}

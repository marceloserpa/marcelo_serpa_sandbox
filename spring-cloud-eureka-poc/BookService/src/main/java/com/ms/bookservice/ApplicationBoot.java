package com.ms.bookservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApplicationBoot {

    public static void main(String[] args){
        SpringApplication.run(ApplicationBoot.class, args);
    }

}

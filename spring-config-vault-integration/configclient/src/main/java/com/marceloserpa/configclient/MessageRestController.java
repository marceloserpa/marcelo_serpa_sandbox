package com.marceloserpa.configclient;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageRestController {

    @Value("${message}")
    private String message; // load from spring-config

    @Value("${foo}")
    private String secret; // load from vault

    @RequestMapping("/message")
    String getMessage() {

        String finalmessage = this.message + " - " + secret;

        return finalmessage;
    }

}

package com.ms.externalservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ping")
public class PingController {

    @RequestMapping(value = "/{numberToPing}")
    public String ping(@PathVariable("numberToPing") Integer numberToPing){
        String message = "Ping " + numberToPing + "!";
        System.out.println(message);
        return message;
    }

}

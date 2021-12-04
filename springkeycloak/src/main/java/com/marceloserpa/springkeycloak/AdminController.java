package com.marceloserpa.springkeycloak;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping(value = "hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello admin");
    }

}

package com.marceloserpa.springkeycloak;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {

    @GetMapping(value = "hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello member");
    }

}

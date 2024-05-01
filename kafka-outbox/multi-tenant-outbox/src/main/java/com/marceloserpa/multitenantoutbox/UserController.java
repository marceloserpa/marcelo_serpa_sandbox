package com.marceloserpa.multitenantoutbox;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public List<User> hello(){
        return List.of(new User(1L, 1L, "marceloserpa"));
    }


}

package com.marceloserpa.multitenantoutbox;

import com.marceloserpa.multitenantoutbox.config.TenantContextHolder;
import com.marceloserpa.multitenantoutbox.config.TenantDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getAll();
    }




}

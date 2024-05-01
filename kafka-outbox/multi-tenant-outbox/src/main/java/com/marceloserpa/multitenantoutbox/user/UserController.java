package com.marceloserpa.multitenantoutbox.user;

import com.marceloserpa.multitenantoutbox.config.TenantContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getAll();
    }

    @PostMapping
    public void save(@RequestBody User user) {
        user.setTenantId(TenantContextHolder.getTenantId());
        userService.save(user);
    }


}

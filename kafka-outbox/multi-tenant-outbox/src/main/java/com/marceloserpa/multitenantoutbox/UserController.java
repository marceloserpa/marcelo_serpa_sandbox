package com.marceloserpa.multitenantoutbox;

import com.marceloserpa.multitenantoutbox.databaseconfig.TenantContextHolder;
import com.marceloserpa.multitenantoutbox.databaseconfig.TenantDatabase;
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
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers(){
        TenantContextHolder.setDatabase(TenantDatabase.DATABASE02);
        return Streamable.of(userRepository.findAll()).toList() ;
    }




}
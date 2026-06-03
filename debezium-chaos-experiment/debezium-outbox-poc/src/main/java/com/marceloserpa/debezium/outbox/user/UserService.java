package com.marceloserpa.debezium.outbox.user;

import com.marceloserpa.debezium.outbox.outbox.OutboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OutboxService<User> outboxService;

    public List<User> getAll(){
        return Streamable.of(userRepository.findAll()).toList() ;
    }

    @Transactional
    public void save(User user)  {
        userRepository.save(user);
        outboxService.save(user);
    }
}

package com.marceloserpa.multitenantoutbox.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marceloserpa.multitenantoutbox.config.TenantContextHolder;
import com.marceloserpa.multitenantoutbox.outbox.OutboxService;
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
    private OutboxService outboxService;

    @Autowired
    private ObjectMapper mapper;

    public List<User> getAll(){
        var tenantId = TenantContextHolder.getTenantId();
        return Streamable.of(userRepository.findByTenantId(tenantId)).toList() ;
    }

    @Transactional
    public void save(User user)  {
        userRepository.save(user);
        String json = null;
        try {
            json = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        outboxService.save(json);
    }
}

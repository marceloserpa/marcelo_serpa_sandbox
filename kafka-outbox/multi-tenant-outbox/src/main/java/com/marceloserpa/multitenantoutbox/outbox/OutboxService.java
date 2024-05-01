package com.marceloserpa.multitenantoutbox.outbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutboxService {

    @Autowired
    private OutboxRepository outboxRepository;

    public void save(String payload) {
        var outbox = new Outbox(null,payload);
        outboxRepository.save(outbox);
    }

}

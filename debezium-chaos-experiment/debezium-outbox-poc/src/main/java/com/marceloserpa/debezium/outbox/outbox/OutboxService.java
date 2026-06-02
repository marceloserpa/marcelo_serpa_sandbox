package com.marceloserpa.debezium.outbox.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class OutboxService<T> {

    private static Logger LOG = Logger.getLogger(OutboxService.class.getName());

    @Autowired
    private OutboxRepository outboxRepository;

    @Autowired
    private ObjectMapper mapper;


    public void save(T t) {
        String json = null;
        try {
            var traceId = UUID.randomUUID();
            LOG.info("[EventProcessed] trace_id="+traceId.toString());
            Payload<T> payload = new Payload<T>(traceId, t);
            json = mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var outbox = new Outbox(null, json);
        outboxRepository.save(outbox);
    }

}

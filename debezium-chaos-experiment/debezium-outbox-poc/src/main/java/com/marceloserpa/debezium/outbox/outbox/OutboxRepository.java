package com.marceloserpa.debezium.outbox.outbox;

import org.springframework.data.repository.CrudRepository;

public interface OutboxRepository extends CrudRepository<Outbox, Long> {
}

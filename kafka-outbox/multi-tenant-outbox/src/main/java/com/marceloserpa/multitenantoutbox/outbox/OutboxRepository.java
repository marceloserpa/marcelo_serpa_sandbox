package com.marceloserpa.multitenantoutbox.outbox;

import org.springframework.data.repository.CrudRepository;

public interface OutboxRepository extends CrudRepository<Outbox, Long> {
}

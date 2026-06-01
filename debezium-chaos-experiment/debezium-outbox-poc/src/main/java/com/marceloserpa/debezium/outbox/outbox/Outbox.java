package com.marceloserpa.debezium.outbox.outbox;

import org.springframework.data.annotation.Id;

public record Outbox(@Id Long id, String payload) {
}

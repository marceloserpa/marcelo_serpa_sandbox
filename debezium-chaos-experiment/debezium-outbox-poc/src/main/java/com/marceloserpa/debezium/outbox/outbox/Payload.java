package com.marceloserpa.debezium.outbox.outbox;

import java.util.UUID;

public record Payload<T>(UUID traceId, T t) {

}

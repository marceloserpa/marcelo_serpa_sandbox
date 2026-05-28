package com.mserpa.ticket;

import java.math.BigDecimal;
import java.util.Optional;

public record Ticket(Show show, Zone zone, Optional<String> seat, BigDecimal price, String buyer) {
}

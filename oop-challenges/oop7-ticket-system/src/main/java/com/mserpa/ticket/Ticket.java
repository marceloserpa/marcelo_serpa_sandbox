package com.mserpa.ticket;

import java.math.BigDecimal;

public record Ticket(Show show, Zone zone, Integer seatNumber, BigDecimal price, String buyer) {
}

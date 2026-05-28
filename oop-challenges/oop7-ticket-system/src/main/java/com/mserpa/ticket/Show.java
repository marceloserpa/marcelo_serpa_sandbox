package com.mserpa.ticket;

import java.time.LocalDate;
import java.util.List;

public record Show(String title, LocalDate date, String venueName, List<Zone> zones) {
}

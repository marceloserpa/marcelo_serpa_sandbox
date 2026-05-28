package com.mserpa.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Main {

    static void main() {
        var vip = new Zone.Assigned("VIP", new BigDecimal("2000.00"), 5, 10);
        var premium = new Zone.Assigned("Premium", new BigDecimal("900.00"), 10, 10);
        var regular = new Zone.GeneralAdmission("Regular", new BigDecimal("400.00"), 500);

        var kornConcert = new Show("Korn", LocalDate.of(2026, 5, 16), "Allianz Park", List.of(vip, premium, regular));

    }
    
}

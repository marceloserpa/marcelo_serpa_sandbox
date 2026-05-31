package com.mserpa.ticket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    static void main() {
        var vip = new Zone.Assigned("VIP", new BigDecimal("2000.00"), 5, 10);
        var premium = new Zone.Assigned("Premium", new BigDecimal("900.00"), 10, 10);
        var regular = new Zone.GeneralAdmission("Regular", new BigDecimal("400.00"), 500);

        var kornConcert = new Show("Korn", LocalDate.of(2026, 5, 16), "Allianz Park", List.of(vip, premium, regular));

        var ticketManager = new TicketMGM();
        ticketManager.register(kornConcert);

        print(ticketManager.purchase("Korn", vip, 1, "marcelo"));
        print(ticketManager.purchase("Korn", regular, null, "bob"));
        print(ticketManager.purchase("Korn", vip, 2, "john"));
        print(ticketManager.purchase("Korn", vip, 99, "dave"));

        PurchaseResult last = null;
        for (int i = 0; i < 501; i++) {
            last = ticketManager.purchase("Korn", regular, null, "ga-" + i);
        }
        print(last);
    }

    static void print(PurchaseResult result) {
        switch (result) {
            case PurchaseResult.Success(Ticket t) -> System.out.println(
                "Success: " + t.buyer() + " -> " + t.zone().name() + " "
                    + t.seatNumber() + " @ " + t.price());
            case PurchaseResult.Failure(String reason) -> System.out.println("Failure: " + reason);
        }
    }
}

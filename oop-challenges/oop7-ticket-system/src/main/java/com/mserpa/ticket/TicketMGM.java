package com.mserpa.ticket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TicketMGM {

    private final Map<String, Show> shows = new HashMap<>();
    private final Map<Zone.Assigned, Set<Integer>> takenSeats = new HashMap<>();
    private final Map<Zone.GeneralAdmission, Integer> gaSold = new HashMap<>();

    public void register(Show show) {
        shows.put(show.title(), show);
    }

    public PurchaseResult purchase(String showTitle, Zone zone, Integer seatNumber, String buyer) {
        Show show = shows.get(showTitle);
        if (show == null) {
            return new PurchaseResult.Failure("show not found");
        }
        if (!show.zones().contains(zone)) {
            return new PurchaseResult.Failure("zone not found");
        }
        return switch (zone) {
            case Zone.Assigned a -> sellAssigned(show, a, seatNumber, buyer);
            case Zone.GeneralAdmission ga -> sellGeralAdmision(show, ga, buyer);
        };
    }

    private PurchaseResult sellAssigned(Show show, Zone.Assigned zone, Integer seatNumber, String buyer) {
        if (seatNumber == null) {
            return new PurchaseResult.Failure("seat number required for VIP zone");
        }

        Set<Integer> taken = takenSeats.computeIfAbsent(zone, _ -> new HashSet<>());
        if (taken.contains(seatNumber)) {
            return new PurchaseResult.Failure("seat taken");
        }
        taken.add(seatNumber);
        return new PurchaseResult.Success(new Ticket(show, zone, seatNumber, zone.price(), buyer));
    }

    private PurchaseResult sellGeralAdmision(Show show, Zone.GeneralAdmission zone, String buyer) {
        int sold = gaSold.getOrDefault(zone, 0);
        if (sold >= zone.capacity()) {
            return new PurchaseResult.Failure("zone is full");
        }
        gaSold.put(zone, sold + 1);
        return new PurchaseResult.Success(new Ticket(show, zone, null, zone.price(), buyer));
    }
}

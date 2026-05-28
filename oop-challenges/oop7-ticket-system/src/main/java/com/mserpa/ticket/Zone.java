package com.mserpa.ticket;

import java.math.BigDecimal;

public sealed interface Zone permits Zone.Assigned, Zone.GeneralAdmission {

    String name();

    BigDecimal price();

    int capacity();

    record Assigned(String name, BigDecimal price, int rows, int seatsPerRow) implements Zone {
        @Override
        public int capacity() {
            return rows * seatsPerRow;
        }
    }

    record GeneralAdmission(String name, BigDecimal price, int capacity) implements Zone {
    }
}

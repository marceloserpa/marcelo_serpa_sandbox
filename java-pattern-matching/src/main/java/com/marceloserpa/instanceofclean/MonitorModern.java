package com.marceloserpa.instanceofclean;

public class MonitorModern {

    String model;
    double price;

    @Override
    public boolean equals(Object o) {
        return o instanceof MonitorModern other &&
            model.equals(other.model) && price == other.price;
    }

}

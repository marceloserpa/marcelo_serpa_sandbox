package com.marceloserpa.instanceofclean;

public class MonitorLegacy {

    String model;
    double price;

    @Override
    public boolean equals(Object o) {
        if (o instanceof MonitorLegacy) {
            MonitorLegacy other = (MonitorLegacy) o;
            return model.equals(other.model) && price == other.price;
        }
        return false;
    }

}

package com.marceloserpa.freight.strategy;

public class FreightFactory {

    public static FreightStrategy getFreight(FreightType type){
        return switch (type) {
            case TRUCK -> new TruckFreight();
            case BOAT -> new BoatFreight();
        };
    }


}

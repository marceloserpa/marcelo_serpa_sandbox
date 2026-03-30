package com.marceloserpa.freight;

import com.marceloserpa.freight.strategy.*;

public class Main {

    static void main() {
        var pkg = new Package(10, 2.07);
        FreightStrategy freightStrategy = FreightFactory.getFreight(FreightType.TRUCK);
        System.out.printf("Truck: %s%n", freightStrategy.calculate(pkg));

        FreightStrategy freight2Strategy = FreightFactory.getFreight(FreightType.BOAT);
        System.out.printf("Boat: %s%n", freight2Strategy.calculate(pkg));
    }
}

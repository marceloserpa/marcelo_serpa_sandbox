package com.marceloserpa.freight;

import com.marceloserpa.freight.strategy.FreightStrategy;
import com.marceloserpa.freight.strategy.TruckFreight;

public class Main {

    static void main() {

        var pkg = new Package(10, 2.07);
        FreightStrategy freightStrategy = new TruckFreight();
        System.out.println(freightStrategy.calculate(pkg));
    }
}

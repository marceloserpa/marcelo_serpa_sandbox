package com.marceloserpa.freight.strategy;

import com.marceloserpa.freight.Package;
import com.marceloserpa.freight.PriceProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class TruckFreight implements FreightStrategy {

    @Override
    public BigDecimal calculate(Package pkg) {
        BigDecimal basePrice = PriceProvider.getPrice("TRUCK").orElse(BigDecimal.ZERO);

        double dimensions = pkg.weight() * pkg.volume();

        return basePrice.multiply(BigDecimal.valueOf(dimensions))
                .multiply(new BigDecimal("1.2"))
                .setScale(2, RoundingMode.HALF_UP);
    }

}

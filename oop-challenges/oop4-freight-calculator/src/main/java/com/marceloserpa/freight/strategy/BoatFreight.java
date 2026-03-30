package com.marceloserpa.freight.strategy;

import com.marceloserpa.freight.Package;
import com.marceloserpa.freight.PriceProvider;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BoatFreight implements FreightStrategy{
    @Override
    public BigDecimal calculate(Package pkg) {
        BigDecimal basePrice = PriceProvider.getPrice(FreightType.BOAT).orElse(BigDecimal.ONE);

        double dimensions = pkg.weight() * pkg.volume();

        return basePrice.multiply(BigDecimal.valueOf(dimensions))
                .setScale(2, RoundingMode.HALF_UP);
    }
}

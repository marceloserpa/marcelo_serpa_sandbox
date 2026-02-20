package com.marceloserpa.freight;

import com.marceloserpa.freight.strategy.FreightType;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PriceProvider {

    private final static ConcurrentHashMap<FreightType, BigDecimal> prices;

    static {
        prices = new ConcurrentHashMap<>();
        prices.put(FreightType.TRUCK, new BigDecimal("2.84"));
        prices.put(FreightType.BOAT, new BigDecimal("0.43"));
    }

    public static Optional<BigDecimal> getPrice(FreightType type){
        return Optional.ofNullable(prices.get(type));
    }

}

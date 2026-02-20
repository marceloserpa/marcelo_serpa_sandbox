package com.marceloserpa.freight;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class PriceProvider {

    private final static ConcurrentHashMap<String, BigDecimal> prices;

    static {
        prices = new ConcurrentHashMap<>();
        prices.put("TRUCK", new BigDecimal("2.84"));
    }

    public static Optional<BigDecimal> getPrice(String mode){
        return Optional.ofNullable(prices.get(mode));
    }

}

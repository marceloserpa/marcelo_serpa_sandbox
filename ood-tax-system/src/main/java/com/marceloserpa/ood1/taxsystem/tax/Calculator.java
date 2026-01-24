package com.marceloserpa.ood1.taxsystem.tax;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Calculator {

    private final static Map<Integer, Map<State, Tax>> taxes;

    static {
        List<Tax> taxes2025 = List.of(
            new Tax("ICMS", State.RS, new BigDecimal("10.5"), Category.ELECTRONIC),
            new Tax("PIS", State.RS, new BigDecimal("2"), Category.ELECTRONIC),
            new Tax("ICMS", State.RS, new BigDecimal("2"), Category.FOOD),
            new Tax("ICMS", State.SP, new BigDecimal("8.1"), Category.ELECTRONIC),
            new Tax("PIS", State.SP, new BigDecimal("3.5"), Category.ELECTRONIC),
            new Tax("ICMS", State.SP, new BigDecimal("1"), Category.FOOD),
            new Tax("COFINS", State.FEDERAL, new BigDecimal("2.5"), Category.ELECTRONIC)
        );

        List<Tax> taxes2026 = List.of(
            new Tax("ICMS", State.RS, new BigDecimal("12.5"), Category.ELECTRONIC),
            new Tax("PIS", State.RS, new BigDecimal("2.5"), Category.ELECTRONIC),
            new Tax("ICMS", State.RS, new BigDecimal("3"), Category.FOOD),
            new Tax("ICMS", State.SP, new BigDecimal("8.0"), Category.ELECTRONIC),
            new Tax("PIS", State.SP, new BigDecimal("3.0"), Category.ELECTRONIC),
            new Tax("ICMS", State.SP, new BigDecimal("1.8"), Category.FOOD),
            new Tax("COFINS", State.FEDERAL, new BigDecimal("3"), Category.ELECTRONIC)
        );

        taxes = new HashMap<>();
        taxes.put(2025, taxes2025.stream().collect(Collectors.toMap(Tax::state, tax -> tax)));
        taxes.put(2026, taxes2026.stream().collect(Collectors.toMap(Tax::state, tax -> tax)));

    }

    private static Map<State, Tax> getTaxes(int year){
        Map<State, Tax> stateTax = taxes.getOrDefault(year, new HashMap<>());
        return Map.copyOf(stateTax);
    }

}

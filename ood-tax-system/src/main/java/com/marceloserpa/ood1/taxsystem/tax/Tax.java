package com.marceloserpa.ood1.taxsystem.tax;

import java.math.BigDecimal;

public record Tax(String name, State state, BigDecimal percentage, Category category) {
}

package com.marceloserpa.ood1.taxsystem.tax;

import java.math.BigDecimal;

public record Tax(TaxType name, State state, int year, BigDecimal percentage, Category category) {
}

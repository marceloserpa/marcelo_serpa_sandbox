package com.marceloserpa.ood1.taxsystem.product;

import com.marceloserpa.ood1.taxsystem.tax.Category;

import java.math.BigDecimal;

public record Product(String name, BigDecimal price, Category category) {
}

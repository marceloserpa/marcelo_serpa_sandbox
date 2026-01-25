package com.marceloserpa.ood1.taxsystem.product;

import com.marceloserpa.ood1.taxsystem.tax.State;

import java.time.LocalDate;
import java.util.List;

public record ShoppingCart(LocalDate date, List<Product> products, State destination)  {
}

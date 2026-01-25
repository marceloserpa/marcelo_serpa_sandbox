package com.marceloserpa.ood1.taxsystem.tax;

import com.marceloserpa.ood1.taxsystem.product.Product;
import com.marceloserpa.ood1.taxsystem.product.ShoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Calculator {

    private static record TaxKey(State state, Integer year, Category category) {
    }

    private static class TaxTable {

        private static final Map<TaxKey, List<Tax>> taxes = new HashMap<>();

        public void put(TaxKey key, Tax value) {
            List<Tax> taxByKey = taxes.get(key);
            if(taxByKey == null) {
                List<Tax> internal = new ArrayList<>();
                internal.add(value);
                taxes.put(key, internal);
                return;
            }
            taxByKey.add(value);
        }

        public List<Tax> get(TaxKey key) {
            List<Tax> taxList = taxes.get(key);
            if(taxList == null){
                return List.of();
            }
            return taxList;
        }
    }

    private final static TaxTable TAX_TABLE = new TaxTable();


    static {
        List<Tax> allTaxes = List.of(
            new Tax("ICMS", State.RS, 2025, new BigDecimal("10.5"), Category.ELECTRONIC),
            new Tax("PIS", State.RS,  2025, new BigDecimal("2"), Category.ELECTRONIC),
            new Tax("ICMS", State.RS,  2025, new BigDecimal("2"), Category.FOOD),
            new Tax("ICMS", State.SP,  2025, new BigDecimal("8.1"), Category.ELECTRONIC),
            new Tax("PIS", State.SP,  2025, new BigDecimal("3.5"), Category.ELECTRONIC),
            new Tax("ICMS", State.SP,  2025, new BigDecimal("1"), Category.FOOD),
            new Tax("COFINS", State.FEDERAL,  2025, new BigDecimal("2.5"), Category.ELECTRONIC),
            new Tax("ICMS", State.RS,  2026, new BigDecimal("12.5"), Category.ELECTRONIC),
            new Tax("PIS", State.RS,  2026, new BigDecimal("2.5"), Category.ELECTRONIC),
            new Tax("ICMS", State.RS,  2026, new BigDecimal("3"), Category.FOOD),
            new Tax("ICMS", State.SP,  2026, new BigDecimal("8.0"), Category.ELECTRONIC),
            new Tax("PIS", State.SP,  2026, new BigDecimal("3.0"), Category.ELECTRONIC),
            new Tax("ICMS", State.SP,  2026, new BigDecimal("1.8"), Category.FOOD),
            new Tax("COFINS", State.FEDERAL,  2026, new BigDecimal("3"), Category.ELECTRONIC)
        );

        for(Tax tax : allTaxes) {
            TAX_TABLE.put(new TaxKey(tax.state(), tax.year(), tax.category()), tax);
        }
    }

    public Map<String, BigDecimal> calculateTaxes(ShoppingCart shoppingCart){
        var year = shoppingCart.date().getYear();
        var state = shoppingCart.destination();

        Map<String, BigDecimal> total = new HashMap<>();

        for(Product product : shoppingCart.products()){
            List<Tax> taxesByProduct = TAX_TABLE.get(new TaxKey(state, year, product.category()));

            for(Tax tax: taxesByProduct) {
                BigDecimal taxApplied = product.price().multiply(tax.percentage())
                        .divide(new BigDecimal("100.00"))
                        .setScale(2, RoundingMode.HALF_UP);

                System.out.println(tax.name() + "-" + taxApplied);
                total.merge(tax.name(), taxApplied, BigDecimal::add);
            }

        }
        return total;

    }

}

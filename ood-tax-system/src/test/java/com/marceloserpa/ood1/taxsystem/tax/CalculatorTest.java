package com.marceloserpa.ood1.taxsystem.tax;

import com.marceloserpa.ood1.taxsystem.product.Product;
import com.marceloserpa.ood1.taxsystem.product.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {


    @Test
    public void test(){
        Calculator calculator = new Calculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("TV", new BigDecimal("1000.00"), Category.ELECTRONIC));

        Map<TaxType, BigDecimal> stringBigDecimalMap = calculator.calculateTaxes(
                new ShoppingCart(LocalDate.of(2026, 01, 01), products, State.RS));

        assertEquals(0, new BigDecimal("125.0").compareTo(stringBigDecimalMap.get(TaxType.ICMS)));
        assertEquals(0, new BigDecimal("25").compareTo(stringBigDecimalMap.get(TaxType.PIS)));
    }


    @Test
    public void test2(){
        Calculator calculator = new Calculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("TV", new BigDecimal("1000.00"), Category.ELECTRONIC));
        products.add(new Product("Remote control", new BigDecimal("165.00"), Category.ELECTRONIC));
        products.add(new Product("Coffee", new BigDecimal("8.50"), Category.FOOD));

        Map<TaxType, BigDecimal> result = calculator.calculateTaxes(
                new ShoppingCart(LocalDate.of(2026, 01, 01), products, State.RS));

        assertEquals(0, new BigDecimal("145.89").compareTo(result.get(TaxType.ICMS)));
        assertEquals(0, new BigDecimal("29.13").compareTo(result.get(TaxType.PIS)));

    }



}
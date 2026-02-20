package com.marceloserpa.freight.strategy;

import com.marceloserpa.freight.Package;

import java.math.BigDecimal;

public interface FreightStrategy {

    BigDecimal calculate(Package pack);
}

package com.marceloserpa.freight.strategy;

import com.marceloserpa.freight.Package;

import java.math.BigDecimal;

public sealed interface FreightStrategy permits BoatFreight, TruckFreight {

    BigDecimal calculate(Package pack);
}

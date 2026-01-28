package com.marceloserpa.enumxrecords.usingrecords;

import java.math.BigDecimal;

public record WireTransfer (
    String cardNumber,
    String routingNumber,
    BigDecimal amount
) implements Payment { }

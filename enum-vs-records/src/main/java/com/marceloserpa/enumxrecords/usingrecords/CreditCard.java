package com.marceloserpa.enumxrecords.usingrecords;

import java.math.BigDecimal;

public record CreditCard (
    String cardNumber,
    String routingNumber,
    String accountNumber,
    BigDecimal amount
) implements Payment {}

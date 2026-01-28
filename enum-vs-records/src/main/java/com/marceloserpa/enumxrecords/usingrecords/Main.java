package com.marceloserpa.enumxrecords.usingrecords;


import java.math.BigDecimal;

public class Main {

    static void main() {
        CreditCard creditCardPayment = new CreditCard("1234560", "741258", "2020", new BigDecimal("100.50"));
        var paymentProcessor =  new PaymentProcessor();
        System.out.println(paymentProcessor.process(creditCardPayment));

    }
}

package com.marceloserpa.enumxrecords.enumway;

import java.math.BigDecimal;

public class Main {

    static void main() {
         var payment = new Payment(Type.CREDIT_CARD, "1234560", "741258", "2020", new BigDecimal("100.50"));
        System.out.println(payment);


        var paymentProcessor =  new PaymentProcessor();
        System.out.println(paymentProcessor.process(payment));

    }
}

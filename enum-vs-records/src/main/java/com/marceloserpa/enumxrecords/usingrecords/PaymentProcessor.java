package com.marceloserpa.enumxrecords.usingrecords;


public class PaymentProcessor {

    public String process(Payment payment) {
        return switch (payment) {
            case CreditCard c -> "Call credit card gateway " + c.amount();
            case WireTransfer wt -> "call transfer gateway" + wt.amount();
        };
    }

}

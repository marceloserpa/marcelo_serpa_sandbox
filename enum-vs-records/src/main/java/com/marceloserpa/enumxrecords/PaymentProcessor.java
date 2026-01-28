package com.marceloserpa.enumxrecords;

import static com.marceloserpa.enumxrecords.Type.CREDIT_CARD;
import static com.marceloserpa.enumxrecords.Type.WIRE_TRANSFER;

public class PaymentProcessor {

    public boolean process(Payment payment) {
        if(CREDIT_CARD.equals(payment.getType())) {
            System.out.println("Call credit card gateway");
            return true;
        } else if (WIRE_TRANSFER.equals(payment.getType())) {
            System.out.println("call transfer gateway");
            return true;
        }
        return false;
    }

}

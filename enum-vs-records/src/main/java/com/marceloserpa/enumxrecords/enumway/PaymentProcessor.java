package com.marceloserpa.enumxrecords.enumway;

import static com.marceloserpa.enumxrecords.enumway.Type.CREDIT_CARD;
import static com.marceloserpa.enumxrecords.enumway.Type.WIRE_TRANSFER;

public class PaymentProcessor {

    public String process(Payment payment) {
        if(CREDIT_CARD.equals(payment.getType())) {
            return "Call credit card gateway " + payment.getAmount();
        } else if (WIRE_TRANSFER.equals(payment.getType())) {
            return "call transfer gateway" + payment.getAmount();
        }
        return "failed";
    }

}

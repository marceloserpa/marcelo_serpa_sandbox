package com.marceloserpa.enumxrecords.enumway;

import java.math.BigDecimal;

public class Payment {

    private final Type type;
    private final String cardNumber;
    private final String routingNumber;
    private final String accountNumber;
    private final BigDecimal amount;

    public Payment(Type type, String cardNumber, String routingNumber, String accountNumber, BigDecimal amount) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "type=" + type +
                ", cardNumber='" + cardNumber + '\'' +
                ", routingNumber='" + routingNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}

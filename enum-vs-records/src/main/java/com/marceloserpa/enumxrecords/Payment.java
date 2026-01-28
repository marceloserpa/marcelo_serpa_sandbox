package com.marceloserpa.enumxrecords;

public class Payment {

    private final Type type;
    private final String cardNumber;
    private final String routingNumber;
    private final String accountNumber;

    public Payment(Type type, String cardNumber, String routingNumber, String accountNumber) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
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

    @Override
    public String toString() {
        return "Payment{" +
                "type=" + type +
                ", cardNumber='" + cardNumber + '\'' +
                ", routingNumber='" + routingNumber + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}

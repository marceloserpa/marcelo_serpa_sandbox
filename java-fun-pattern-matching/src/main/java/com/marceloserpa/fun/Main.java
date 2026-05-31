package com.marceloserpa.fun;

import java.math.BigDecimal;

public class Main {
    static void main() {
        Result<Payment> paymentResult = processPayment(new Payment(new BigDecimal("8")));
        printResult(paymentResult);

        Result<Payment> paymentResult2 = processPayment(new Payment(new BigDecimal("11")));
        printResult(paymentResult2);
    }

    public static void printResult(Result<Payment> result) {
        switch (result) {
            case Result.Success(var value) -> System.out.println("Payment processed: " + value);
            case Result.Failed(var error) -> System.out.println("Error " + error);
            default -> System.out.println("Invalid");
        }
    }

    public static Result<Payment> processPayment(Payment payment){
        if(new BigDecimal("10").compareTo(payment.amount()) < 0){
            return new Result.Failed("error");
        }
        return new Result.Success<>(payment);
    }
}
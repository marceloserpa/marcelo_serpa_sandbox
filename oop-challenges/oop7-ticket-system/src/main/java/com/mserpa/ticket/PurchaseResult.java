package com.mserpa.ticket;

public sealed interface PurchaseResult permits PurchaseResult.Success, PurchaseResult.Failure {

    record Success(Ticket ticket) implements PurchaseResult {
    }

    record Failure(String reason) implements PurchaseResult {
    }
}

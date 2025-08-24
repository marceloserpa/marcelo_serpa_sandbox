package com.marceloserpa.kafkafun.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID userId;
    private LocalDateTime date;
    private List<Cart> cart;
    private Payment payment;


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Cart> getCart() {
        return cart;
    }

    public void setCart(List<Cart> cart) {
        this.cart = cart;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}

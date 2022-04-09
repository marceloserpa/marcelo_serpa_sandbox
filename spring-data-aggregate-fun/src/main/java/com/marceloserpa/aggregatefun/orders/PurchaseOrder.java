package com.marceloserpa.aggregatefun.orders;

import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;

public class PurchaseOrder {

    private @Id
    Long id;
    private String shippingAddress;
    private Set<OrderItem> items = new HashSet<>();

    void addItem(int quantity, String product) {
        items.add(createOrderItem(quantity, product));
    }

    private OrderItem createOrderItem(int quantity, String product) {

        OrderItem item = new OrderItem();
        item.product = product;
        item.quantity = quantity;
        return item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
}

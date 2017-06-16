package com.mserpa.esperpoc.events;


public class OrderEvent {

    private String itemName;
    private Double price;

    public OrderEvent() {
    }

    public OrderEvent(String itemName, Double price) {
        this.itemName = itemName;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

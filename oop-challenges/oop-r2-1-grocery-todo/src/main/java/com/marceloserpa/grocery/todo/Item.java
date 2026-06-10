package com.marceloserpa.grocery.todo;

public class Item {

    public Item(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Item(String product, Integer quantity, boolean completed) {
        this.product = product;
        this.quantity = quantity;
        this.completed = completed;
    }

    private String product;
    private Integer quantity;
    private boolean completed;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String toString(){
        return "product: '%s' quantity: %d completed: %s".formatted(product, quantity, completed) ;
    }

}

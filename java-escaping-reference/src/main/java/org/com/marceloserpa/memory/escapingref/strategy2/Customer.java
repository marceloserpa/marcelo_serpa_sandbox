package org.com.marceloserpa.memory.escapingref.strategy2;

public class Customer {

    public Customer(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
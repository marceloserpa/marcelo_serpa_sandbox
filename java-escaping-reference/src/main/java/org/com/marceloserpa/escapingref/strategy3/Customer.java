package org.com.marceloserpa.escapingref.strategy3;

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

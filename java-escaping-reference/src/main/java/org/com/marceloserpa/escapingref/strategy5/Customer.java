package org.com.marceloserpa.escapingref.strategy5;

public class Customer implements CustomerReadOnly {

    public Customer(String name) {
        this.name = name;
    }

    // Constructor to allow to create a new copy from another object.
    public Customer(Customer c) {
        this.name = c.getName();
    }

    private String name;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

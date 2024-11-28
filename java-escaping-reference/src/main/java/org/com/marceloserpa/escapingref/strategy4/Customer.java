package org.com.marceloserpa.escapingref.strategy4;

import org.com.marceloserpa.escapingref.problem.CustomerRecords;

public class Customer {

    public Customer(String name) {
        this.name = name;
    }

    // Constructor to allow to create a new copy from another object.
    public Customer(Customer c) {
        this.name = c.getName();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

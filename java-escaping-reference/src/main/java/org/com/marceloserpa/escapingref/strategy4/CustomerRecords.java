package org.com.marceloserpa.escapingref.strategy4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CustomerRecords {

    private Map<String, Customer> records;

    public CustomerRecords() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        this.records.put(customer.getName(), customer);
    }

    public Map<String, Customer> getRecords() {
        return Map.copyOf(this.records);
    }

    public Customer find(String name) {
        return records.get(name);
    }

    public Customer findFixed(String name) {
        return new Customer(records.get(name));
    }
}

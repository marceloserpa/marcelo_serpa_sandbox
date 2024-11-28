package org.com.marceloserpa.escapingref.strategy2;

import java.util.HashMap;
import java.util.Iterator;
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
        return new HashMap<>(this.records);
    }
}

package org.com.marceloserpa.escapingref.strategy1;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CustomerRecords implements Iterable<Customer> {

    private Map<String, Customer> records;

    public CustomerRecords() {
        this.records = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        this.records.put(customer.getName(), customer);
    }

    @Override
    public Iterator<Customer> iterator() {
        return this.records.values().iterator();
    }
}

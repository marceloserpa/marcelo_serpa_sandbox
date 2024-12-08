package org.com.marceloserpa.escapingref.strategy5;

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

    public Iterator<CustomerReadOnly> getRecords() {
        return this.records.values().stream().map(customer -> (CustomerReadOnly) customer).iterator();
    }

    public CustomerReadOnly find(String name) {
        return new Customer(records.get(name));
    }

}

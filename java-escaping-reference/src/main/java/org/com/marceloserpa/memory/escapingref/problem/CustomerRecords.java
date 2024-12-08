package org.com.marceloserpa.memory.escapingref.problem;

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

    public Map<String, Customer> getCustomers(){
        return this.records;
    }
}

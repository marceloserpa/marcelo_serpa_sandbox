package org.com.marceloserpa.memory.escapingref.strategy3;

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
        /**
         *  Java 9 and below
         * Collections.unmodifiableMap(this.records) will wrapper the original Map allowing only read-operation.
         * Changes on original Map will be visible on returned ImmutableMap
         *
         * Java10+ Map.copyOf()
         * It will create a new instance of Map and copy all references to it. No changes done into original collection
         * will reflect on it BUT the new collection will allocated more memory.
         *
          */

        return Collections.unmodifiableMap(this.records);
    }
}

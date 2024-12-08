package org.com.marceloserpa.memory.escapingref.strategy2;

import java.util.Map;


public class Main {

    public static void main(String[] args) {

        System.out.println("Strategy 2: Duplicating Collection");

        CustomerRecords customerRecords = new CustomerRecords();
        customerRecords.addCustomer(new Customer("A"));
        customerRecords.addCustomer(new Customer("B"));

        // receives a copy of map
        System.out.println("Get records");
        Map<String, Customer> records = customerRecords.getRecords();

        for(String username : records.keySet()) {
            System.out.println(username);
        }

        records.clear();

        System.out.println("Get records after cleaned the map");
        for(String username : records.keySet()) {
            System.out.println(username);
        }

        System.out.println("Get records by invoke the method from CustomerRecords. It will returns a new copy");
        for(String username : customerRecords.getRecords().keySet()) {
            System.out.println(username);
        }

        /*
        This solution will include a performance overhead. We should run performance test.
        Keep in mind, it will only copy the collection BUT it will point to original objects.

        Another important item is the collection created here will short-lived. The memory space will be freed faster.
         */


    }
}

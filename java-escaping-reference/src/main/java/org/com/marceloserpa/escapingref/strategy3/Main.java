package org.com.marceloserpa.escapingref.strategy3;

import java.util.Map;


public class Main {

    public static void main(String[] args) {

        System.out.println("Strategy 3: Return unmodifiable collections");

        CustomerRecords customerRecords = new CustomerRecords();
        customerRecords.addCustomer(new Customer("A"));
        customerRecords.addCustomer(new Customer("B"));

        // receives a copy of map
        System.out.println("Get records");
        Map<String, Customer> records = customerRecords.getRecords();

        for(String username : records.keySet()) {
            System.out.println(username);
        }

        System.out.println("Add a new records using CustomerRecords api");
        customerRecords.addCustomer(new Customer("Z"));

        System.out.println("Get records again");
        for(String username : records.keySet()) {
            System.out.println(username);
        }

        System.out.println("Invoke clear will throw exception.");
        records.clear();

        /*
        This solution will include a performance overhead. We should run performance test.
        Keep in mind, it will only copy the collection BUT it will point to original objects.

        Another important item is the collection created here will short-lived. The memory space will be freed faster.
         */


    }
}

package org.com.marceloserpa.memory.escapingref.strategy4;

import java.util.Map;


public class Main {

    public static void main(String[] args) {

        System.out.println("Strategy 4: Find method returning object copy");

        CustomerRecords customerRecords = new CustomerRecords();
        customerRecords.addCustomer(new Customer("A"));
        customerRecords.addCustomer(new Customer("B"));

        // receives a copy of map
        System.out.println("Get records");
        Map<String, Customer> records = customerRecords.getRecords();

        for(Customer customer : records.values()) {
            System.out.println(customer.getName());
        }

        System.out.println("Find customer B and mutated to C");
        customerRecords.find("B").setName("C");

        System.out.println("Get records after changed B to C");
        for(Customer customer : records.values()) {
            System.out.println(customer.getName());
        }

        System.out.println("Find customer using the method findFixed B and mutated to D");
        customerRecords.findFixed("B").setName("D");

        System.out.println("Get records after changed B (the key remains the same) to D");
        for(Customer customer : records.values()) {
            System.out.println(customer.getName());
        }

    }
}

package org.com.marceloserpa.memory.escapingref.problem;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CustomerRecords customerRecords = new CustomerRecords();
        customerRecords.addCustomer(new Customer("A"));
        customerRecords.addCustomer(new Customer("B"));

        // getCustomers returns the reference for Customer Records map
        Map<String, Customer> customers = customerRecords.getCustomers();
        System.out.println(customers.keySet());

        /*
        it violates the encapsulation allowing external classes to change the
         internal state directly

         */
        customers.clear();

        // Empty map
        System.out.println(customers.keySet());


    }
}

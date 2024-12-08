package org.com.marceloserpa.memory.escapingref.strategy1;

import java.util.Iterator;


public class Main {

    public static void main(String[] args) {

        System.out.println("Strategy 1: Return iterator instead the original collection");

        CustomerRecords customerRecords = new CustomerRecords();
        customerRecords.addCustomer(new Customer("A"));
        customerRecords.addCustomer(new Customer("B"));


        // returning iterator will limit the usage of collection
        for(Customer customer : customerRecords) {
            System.out.println(customer.getName());
        }

        // this not a perfect solution, we can manipulate the iterator content
        Iterator<Customer> iterator = customerRecords.iterator();
        iterator.next();
        iterator.remove();

        // A was removed from iterator
        for(Customer customer : customerRecords) {
            System.out.println(customer.getName());
        }

        // Performance will not be impacted


    }
}

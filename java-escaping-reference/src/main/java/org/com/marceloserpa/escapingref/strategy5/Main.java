package org.com.marceloserpa.escapingref.strategy5;

import java.util.Iterator;
import java.util.Map;


public class Main {

    public static void main(String[] args) {

        System.out.println("Strategy 5: ReadOnly Interface");

        CustomerRecords customerRecords = new CustomerRecords();
        customerRecords.addCustomer(new Customer("A"));
        customerRecords.addCustomer(new Customer("B"));

        // receives a copy of map
        System.out.println("Get records");
        Iterator<CustomerReadOnly> records = customerRecords.getRecords();

        for (Iterator<CustomerReadOnly> it = records; it.hasNext(); ) {
            CustomerReadOnly customer = it.next();
            System.out.println(customer.getName());
        }

        System.out.println("If we try to modify the state, the code will not compile.");
        // customerRecords.find("B").setName("C");

        CustomerReadOnly customerReadOnly = customerRecords.find("B");
        System.out.println("ReadOnly Ref = " + customerReadOnly.getName());
        Customer customerMutable = (Customer) customerReadOnly;
        System.out.println("Using casting to get a mutable object and change the internal state.");
        customerMutable.setName("X");

        System.out.println("get records again");
        records = customerRecords.getRecords();
        for (Iterator<CustomerReadOnly> it = records; it.hasNext(); ) {
            CustomerReadOnly customer = it.next();
            System.out.println(customer.getName());
        }


    }
}

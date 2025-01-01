package org.com.marceloserpa.memory.softleaks;

import java.util.ArrayList;
import java.util.List;

public class SimulateGC {

    public static void main(String[] args) {

        List<Customer> customers = new ArrayList<>();

        while(true){
            Customer customer = new Customer("Marcelo");
            customers.add(customer);
            if(customers.size() > 10000) {
                for(int i = 0; i < 5000; i++){
                    customers.remove(0);
                }
            }
        }

    }
}

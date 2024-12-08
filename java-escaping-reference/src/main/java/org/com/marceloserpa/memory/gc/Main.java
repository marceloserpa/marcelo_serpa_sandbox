package org.com.marceloserpa.memory.gc;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Runtime runtime = Runtime.getRuntime();

        long availableBytes = runtime.freeMemory();
        System.out.println("Available memory at start: " + availableBytes / 1024 + "k");

        // create many objects inside list
        List<Customer> customers = new ArrayList<>();

        for (int i=0; i<1000000; i++) {
            customers.add(new Customer("John"));
        }

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  when customers created: " + availableBytes / 1024 + "k");

        // force customers lose the reference to all objects
        customers = new ArrayList<>();

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  when customers no longer referenced: " + availableBytes / 1024 + "k");

        Thread.sleep(1000);

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  1 second later: " + availableBytes / 1024 + "k");

        System.gc();

        availableBytes = runtime.freeMemory();
        System.out.println("Available memory  after GC command: " + availableBytes / 1024 + "k");


        /**
         * java Main
         *
         * Available memory at start: 1036381k
         * Available memory  when customers created: 1003611k
         * Available memory  when customers no longer referenced: 1003611k
         * Available memory  1 second later: 1003611k
         * Available memory  after GC command: 80730k ?????

        why after GC ran we have less memory available than in beginning?

        answer: In Java 11 or above the behavior of JVM was created. When the JVM has memory that is not being used,
        JVM can give some memory available back to S.O.

         This behaviour can introduce performance if you application give back to S.O. the memory available BUT soon
         need to request again. Each request causes impact on JVM performance.

         Adding -Xms1g -Xmx2g JVM flags on java execution

         Available memory at start: 1048544k
         Available memory  when customers created: 1015264k
         Available memory  when customers no longer referenced: 1015264k
         Available memory  1 second later: 1015264k
         Available memory  after GC command: 1047386k <----

         The available memory now is very close from initial because using -Xms1g (shortcut for -XX:InitialHeapSize)
         we force JVM to have available 1g of memory

         */

    }

}

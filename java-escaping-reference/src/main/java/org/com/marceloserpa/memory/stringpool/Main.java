package org.com.marceloserpa.memory.stringpool;

public class Main {

    public static void main(String[] args) {

        // Strings created with the same value are stored into String Pool
        // StringPool lives in Heap
        String a = "Hello";
        String b = "Hello";

        System.out.println(a.equals(b)); // same value
        System.out.println(a == b); // same reference in memory

        Integer num = 40;
        String c = num.toString(); // calculated values are not stored in StringPool
        String d = "40";

        System.out.println(c.equals(d)); // same value
        System.out.println(c == d); // different reference in memory


        Integer num2 = 41;

        // intern will look at StringPool for a String with same value returning their reference.
        // If not found, it will add the String into StringPool
        String e = num.toString().intern();
        String f = "40";

        System.out.println(e.equals(f)); // same value
        System.out.println(e == f); // same reference in memory

    }

}

package com.marceloserpa.fun.builder;

public class Application {

    public static void main(String[] args) {
        Address address = new Address.Builder().country("Brazil")
                .city("Canoas")
                .street("Street top")
                .zipCode("99999-88")
                .build();

        System.out.println(address);
    }
}

package com.marceloserpa.fun.simple;



public class Application {

    public static void main(String[] args) {

        Address address = new Address("Brazil", "Canoas", "Street top", "88888-555");
        User user = new User("marcelo@serpa.com", "Marcelo Serpa", address);

        System.out.println(user.toString());

        System.out.println("name: " + user.name());
        System.out.println("email: " + user.email());
        System.out.println("country: " + user.address().country());

    }


}

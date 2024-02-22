package com.marceloserpa.instanceofclean;

record Citizen(String name, int age){};

public class ProcessCitizen {
    boolean isElegibleToVote(Object o){
        return o instanceof Citizen(String name, int age)
                && age >= 18;
    }

}

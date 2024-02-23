package com.marceloserpa.guardpattern;

public class Application {


    public static void main(String[] args) {

        System.out.println(damageCalc(new AirPollution()));
    }

    public static int damageCalc(Object o){
        return switch (o) {
            case AirPollution airPollution when airPollution.getAQI() > 200 -> 500;
            case Discrimination discrimination -> discrimination.damagingGenerations();
            case Deforestation deforestation -> deforestation.getTreeDamage();
            case null, default -> -1;
        };

    }

}

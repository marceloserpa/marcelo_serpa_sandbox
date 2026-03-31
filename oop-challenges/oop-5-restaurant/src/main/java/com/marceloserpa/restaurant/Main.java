package com.marceloserpa.restaurant;

import java.util.List;
import java.util.Map;

public class Main {
    
    static void main() {
        Dish burger = new Dish("Burger", List.of(
            new Step(KitchenStationType.CUTTING, 1),
            new Step(KitchenStationType.GRILL, 3),
            new Step(KitchenStationType.PLATING_TABLE, 1)
        ));

        Dish doubleBurger = new Dish("Double Burger", List.of(
            new Step(KitchenStationType.CUTTING, 1),
            new Step(KitchenStationType.GRILL, 6),
            new Step(KitchenStationType.PLATING_TABLE, 1)
        ));

        Dish potatoFries = new Dish("Simple Burguer", List.of(
            new Step(KitchenStationType.CUTTING, 1),
            new Step(KitchenStationType.FRYER, 2),
            new Step(KitchenStationType.PLATING_TABLE, 1)
        ));

        Map<String, Dish> menu = Map.of(
                "SIMPLE_BURGER", burger,
                "DOUBLE_BURGER", doubleBurger,
                "FRIES", potatoFries);

        Kitchen kitchen = new Kitchen();
        System.out.println("Time: " + kitchen.estimate(burger));
        System.out.println("Time: " + kitchen.estimate(burger));
        System.out.println("Time: " + kitchen.estimate(potatoFries));

    }
}

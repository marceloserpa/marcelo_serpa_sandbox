package com.marceloserpa.guitarfactory;

public class Main {
    static void main() {

        GuitarFactory factory = new GuitarFactory();

        GuitarSpec spec = new GuitarSpec(WoodType.MAHOGANY, WoodType.ROASTER_MAPPLE,
                PickupType.HUMBUCKERS, 2, GuitarColor.RED);

        Guitar guitar = factory.create(
                GuitarFactory.TYPE.ELECTRIC,
                spec,
                "Strat Pro"
        );

        System.out.println(guitar);


    }
}

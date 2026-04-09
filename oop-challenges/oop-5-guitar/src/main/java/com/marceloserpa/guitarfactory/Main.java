package com.marceloserpa.guitarfactory;

import com.marceloserpa.guitarfactory.components.GuitarColor;
import com.marceloserpa.guitarfactory.components.GuitarSpec;
import com.marceloserpa.guitarfactory.components.PickupType;
import com.marceloserpa.guitarfactory.components.WoodType;
import com.marceloserpa.guitarfactory.guitars.Guitar;
import com.marceloserpa.guitarfactory.guitars.GuitarFactory;

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

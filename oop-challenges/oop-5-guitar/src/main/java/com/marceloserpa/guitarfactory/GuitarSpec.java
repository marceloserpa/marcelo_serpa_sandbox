package com.marceloserpa.guitarfactory;

public record GuitarSpec(
        WoodType bodyWood,
        WoodType neckWood,
        PickupType pickup,
        int strings,
        GuitarColor color
) {}
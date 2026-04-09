package com.marceloserpa.guitarfactory.components;

public record GuitarSpec(
        WoodType bodyWood,
        WoodType neckWood,
        PickupType pickup,
        int strings,
        GuitarColor color
) {}
package com.marceloserpa.guitarfactory.guitars;

import com.marceloserpa.guitarfactory.components.GuitarSpec;

public sealed interface Guitar
        permits ElectricGuitar, AcousticGuitar, BassGuitar {
    String serialNumber();
    GuitarSpec specs();
    String model();
}
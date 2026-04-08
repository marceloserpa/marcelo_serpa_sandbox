package com.marceloserpa.guitarfactory;

public sealed interface Guitar
        permits ElectricGuitar, AcousticGuitar, BassGuitar {
    String serialNumber();
    GuitarSpec specs();
    String model();
}
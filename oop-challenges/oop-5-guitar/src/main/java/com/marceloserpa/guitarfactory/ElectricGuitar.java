package com.marceloserpa.guitarfactory;

public record ElectricGuitar(
        String serialNumber,
        GuitarSpec specs,
        String model
) implements Guitar {}
package com.marceloserpa.guitarfactory;

public record AcousticGuitar(
        String serialNumber,
        GuitarSpec specs,
        String model
) implements Guitar {}
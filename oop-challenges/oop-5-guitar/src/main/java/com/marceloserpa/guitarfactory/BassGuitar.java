package com.marceloserpa.guitarfactory;

public record BassGuitar(
        String serialNumber,
        GuitarSpec specs,
        String model
) implements Guitar {}
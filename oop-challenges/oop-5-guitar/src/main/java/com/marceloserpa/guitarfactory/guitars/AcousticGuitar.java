package com.marceloserpa.guitarfactory.guitars;

import com.marceloserpa.guitarfactory.components.GuitarSpec;

public record AcousticGuitar(
        String serialNumber,
        GuitarSpec specs,
        String model
) implements Guitar {}
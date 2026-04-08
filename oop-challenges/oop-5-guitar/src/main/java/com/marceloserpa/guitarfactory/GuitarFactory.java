package com.marceloserpa.guitarfactory;

public class GuitarFactory {

    public enum TYPE { ELECTRIC, ACOUSTIC, BASS}

    public Guitar create(TYPE type, GuitarSpec spec, String model) {
        return switch (type) {
            case ELECTRIC -> new ElectricGuitar("A", spec, model);
            case ACOUSTIC -> new AcousticGuitar("B", spec, model);
            case BASS -> new BassGuitar("C", spec, model);
        };
    }
}

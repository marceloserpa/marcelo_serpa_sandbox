package com.marceloserpa.guitarfactory.guitars;

import com.marceloserpa.guitarfactory.components.GuitarSpec;
import java.util.concurrent.atomic.AtomicInteger;

public class GuitarFactory {

    public enum TYPE {
        ELECTRIC("EL"),
        ACOUSTIC("AC"),
        BASS("BA");

        private final String prefix;
        TYPE(String prefix) { this.prefix = prefix; }
        public String prefix() { return prefix; }
    }

    private final AtomicInteger counter = new AtomicInteger(1);

    public Guitar create(TYPE type, GuitarSpec spec, String model) {
        return switch (type) {
            case ELECTRIC -> new ElectricGuitar(generateSerial(type), spec, model);
            case ACOUSTIC -> new AcousticGuitar(generateSerial(type), spec, model);
            case BASS     -> new BassGuitar(generateSerial(type), spec, model);
        };
    }

    private String generateSerial(TYPE type) {
        return "%s-%03d".formatted(type.prefix(), counter.getAndIncrement());
    }
}

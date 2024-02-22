package com.marceloserpa.separatecompsideeffect;

import java.util.Collection;

public class PrinterModern {
    public void printObject(Object obj) {
        System.out.println(switch (obj) {
            case String s -> "String: \"" + s + "\"";
            case Collection<?> c -> "Collection (size = " + c.size() + ")";
            case null, default -> "Other object: " + obj;
        });
    }

}

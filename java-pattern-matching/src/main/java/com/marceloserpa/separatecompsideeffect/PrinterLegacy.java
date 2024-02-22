package com.marceloserpa.separatecompsideeffect;

import java.util.Collection;

public class PrinterLegacy {

    public void printObject(Object obj) {
        if (obj instanceof String s) {
            System.out.println("String: \"" + s + "\"");
        } else if (obj instanceof Collection<?> c) {
            System.out.println("Collection (size = " + c.size() + ")");
        } else {
            System.out.println("Other object: " + obj);
        }
    }

}

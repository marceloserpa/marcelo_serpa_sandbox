package com.marceloserpa.longifelse;

public class Application {

    public static void main(String[] args) {

        Long number = 10L;
        var result = ConverterLegacy.getValueText(number);
        System.out.println(result);

    }
}

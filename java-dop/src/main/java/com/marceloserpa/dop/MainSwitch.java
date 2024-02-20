package com.marceloserpa.dop;

import java.nio.file.Path;

public class MainSwitch {

    public static void main(String[] args) {
        Option option = new Option.InputFile(Path.of("myfile.txt"));

        switch (option) {
            case Option.InputFile(var path):
                System.out.println("input: " + path);
                break;
            case Option.OutputFile(var path):
                System.out.println("output" + path);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + option);
        }

    }
}

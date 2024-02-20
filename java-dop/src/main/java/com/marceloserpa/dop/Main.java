package com.marceloserpa.dop;

import java.nio.file.Path;

public class Main {
    
    public static void main(String[] args) {
        var option = new Option.InputFile(Path.of("input.txt"));

        System.out.println("Starting");

        if(option instanceof Option.InputFile inputFile){
            System.out.println("Input file: " + inputFile.path());
        }

        System.out.println("Completed");
    }
}
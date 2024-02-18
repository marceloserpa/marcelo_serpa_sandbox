package com.marceloserpa;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BufferedReaderFun {

    private static class Measurement {
        String city;
        double min;
        double max;

        double avg;
    }

    public static void main(String args[]) {
        var filename = "./measurements.txt";
        List<String> lines = new ArrayList<>();

        Map<String, Measurement> metrics = new HashMap<>();

        try (FileInputStream fileInputStream = new FileInputStream(filename);
             InputStreamReader reader = new InputStreamReader(fileInputStream);
             BufferedReader buffReader = new BufferedReader(reader)) {

            String line;
            String[] segments = new String[2];
            while ((line = buffReader.readLine()) != null) {
                segments = line.split(";");

                Measurement measurement = metrics.get(segments[0]);
                if(measurement == null) {
                    measurement = new Measurement();
                    measurement.city = segments[0];
                    measurement.min = Double.parseDouble(segments[1]);
                    measurement.max = Double.parseDouble(segments[1]);
                    //measurement.avg = Double.parseDouble(segments[1]);
                } else {
                    if(Double.parseDouble(segments[1]) < measurement.min) {
                        measurement.min = Double.parseDouble(segments[1]);
                    }

                    if(Double.parseDouble(segments[1]) > measurement.max) {
                        measurement.max = Double.parseDouble(segments[1]);
                    }


                }

                metrics.put(segments[0], measurement);







            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(metrics);
    }
}
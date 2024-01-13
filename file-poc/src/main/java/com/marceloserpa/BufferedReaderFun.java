package com.marceloserpa;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BufferedReaderFun {

    public static void main(String args[]) {
        var filename = "./metrics.txt";
        List<String> lines = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filename);
             InputStreamReader reader = new InputStreamReader(fileInputStream);
             BufferedReader buffReader = new BufferedReader(reader)) {

            String line;
            while ((line = buffReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(lines);
    }
}
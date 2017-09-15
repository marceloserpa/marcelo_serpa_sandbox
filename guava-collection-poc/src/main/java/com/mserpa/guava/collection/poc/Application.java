package com.mserpa.guava.collection.poc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        Multimap<String, String> relationships = ArrayListMultimap.create();
        relationships.put("pc-001", "pc-001-cpu");
        relationships.put("pc-001", "pc-001-memory");
        relationships.put("pc-001", "pc-001-disc");

        relationships.put("pc-002", "pc-002-cpu");
        relationships.put("pc-002", "pc-002-memory");
        relationships.put("pc-002", "pc-002-disc");

        relationships.put("pc-003", "pc-003-cpu");
        relationships.put("pc-003", "pc-003-memory");
        relationships.put("pc-003", "pc-003-disc");

        relationships.put("lab-a", "pc-001");
        relationships.put("lab-a", "pc-002");

    }

    public static List<String> getChildren(Multimap<String, String> relationships){
        return null;
    }

}

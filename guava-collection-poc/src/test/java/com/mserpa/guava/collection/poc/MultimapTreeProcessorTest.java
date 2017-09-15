package com.mserpa.guava.collection.poc;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class MultimapTreeProcessorTest {

    @Test
    public void getChildrenSimulateSimpleTree(){
        Multimap<String, String> relationships = ArrayListMultimap.create();
        relationships.put("pc-001", "pc-001-cpu");
        relationships.put("pc-001", "pc-001-memory");
        relationships.put("pc-001", "pc-001-disc");

        MultimapTreeProcessor processor = new MultimapTreeProcessor(relationships);

        List<String> children = processor.getChildren("pc-001");
        assertTrue(3 == children.size());
    }

    @Test
    public void getChildrenSimulateTree(){
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

        MultimapTreeProcessor processor = new MultimapTreeProcessor(relationships);

        List<String> children = processor.getChildren("lab-a");
        assertTrue(8 == children.size());
    }

}
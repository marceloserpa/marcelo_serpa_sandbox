package com.mserpa.guava.collection.poc;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultimapTreeProcessor {

    private Multimap<String, String> tree;

    public MultimapTreeProcessor(Multimap<String, String> tree) {
        this.tree = tree;
    }

    public List<String> getChildren(String node){
        if(Objects.isNull(tree.get(node))) return Lists.newArrayList();

        return tree.get(node).stream()
            .map(child -> {
                List<String> children = getChildren(child);
                children.add(child);
                return children;
            })
            .flatMap(f -> f.stream())
            .collect(Collectors.toList());
    }

}

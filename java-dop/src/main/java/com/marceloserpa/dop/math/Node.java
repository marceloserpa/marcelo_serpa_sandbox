package com.marceloserpa.dop.math;

sealed interface Node {

}


sealed interface BinaryNode extends Node {
    Node left();
    Node right();
}

record AddNode(Node left, Node right) implements BinaryNode { }

record SubNode(Node left, Node right) implements BinaryNode { }
record NegNode(Node node) implements Node { }
record ConstNode(double val) implements Node { }
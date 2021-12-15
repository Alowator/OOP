package ru.nsu.alowator.Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    Tree<String> tree;

    @BeforeEach
    void tree() {
        tree = new Tree<>();
    }

    @Test
    void add() {
        tree.add("A");
    }

}
package ru.nsu.alowator.Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    Tree<String> tree;
    List<String> values;

    @BeforeEach
    void tree() {
        tree = new Tree<>();

        values = new ArrayList<>();
        values.add("A");
        values.add("B");
        values.add("C");
    }

    @Test
    void size() {
        assertEquals(0, tree.size());
        tree.add("A");
        assertEquals(1, tree.size());
        tree.add("B");
        assertEquals(2, tree.size());
    }

    @Test
    void isEmpty() {
        assertTrue(tree.isEmpty());
        tree.add("A");
        assertFalse(tree.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(tree.contains("A"));
        tree.add("A");
        assertTrue(tree.contains("A"));
    }

    @Test
    void containsAll_true() {
        tree.addAll(values);

        assertTrue(tree.containsAll(values));
    }

    @Test
    void containsAll_false() {
        tree.add(values.get(0));

        assertFalse(tree.containsAll(values));
    }

    @Test
    void getSubtree() {
        tree.add("A");
        tree.add("A", "B");
        tree.add("A", "C");
        tree.add("B", "D");
        tree.add("B", "E");

        Tree<String> newTree = tree.getSubtree("B");

        assertTrue(newTree.contains("B"));
        assertTrue(newTree.contains("D"));
        assertTrue(newTree.contains("E"));
        assertFalse(newTree.contains("A"));
        assertFalse(newTree.contains("C"));

    }

    @Test
    void getSubtree_null() {
        tree.add("A");

        assertNull(tree.getSubtree("B"));

    }

    @Test
    void add() {
        tree.add("A");
        assertTrue(tree.contains("A"));
    }

    @Test
    void add_false() {
        tree.add("A");
        assertFalse(tree.add("A"));
    }

    @Test
    void addA() {
        tree.add("A");
        assertTrue(tree.add("A", "B"));
    }

    @Test
    void addA_false() {
        assertFalse(tree.add("B", "A"));
    }

    @Test
    void addAll() {
        assertTrue(tree.addAll(values));
        for (String x : values) {
            assertTrue(tree.contains(x));
        }
    }

    @Test
    void remove() {
        tree.add("A");
        assertTrue(tree.remove("A"));
        assertFalse(tree.contains("A"));
    }

    @Test
    void remove_false() {
        tree.add("A");
        assertTrue(tree.remove("A"));
        assertFalse(tree.remove("A"));
    }

    @Test
    void removeAll() {
        tree.addAll(values);

        assertTrue(tree.removeAll(values));
        assertTrue(tree.isEmpty());
    }

    @Test
    void clear() {
        tree.add("A");
        tree.add("B");

        tree.clear();

        assertTrue(tree.isEmpty());
    }

    @Test
    void retainAll() {
        tree.addAll(values);
        tree.add("D");
        tree.add("E");

        tree.retainAll(values);

        assertEquals(3, tree.size());
        assertTrue(tree.containsAll(values));
    }

    @Test
    void toArray() {
        tree.addAll(values);
        String[] expected = new String[]{"A", "B", "C"};
        assertArrayEquals(expected, tree.toArray());
    }

    @Test
    void toArrayA() {
        tree.addAll(values);
        String[] expected = new String[]{"A", "B", "C"};

        String[] newArr = new String[]{};

        assertArrayEquals(expected, tree.toArray(newArr));
    }

    @Test
    void toArrayA_large() {
        tree.addAll(values);

        String[] arr = new String[10];

        assertArrayEquals(arr, tree.toArray(arr));
    }

    @Test
    void iterator() {
        tree.addAll(values);
        for (String x : tree) {
            assertTrue(values.contains(x));
        }
    }

}
package ru.nsu.alowator.impl;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ImplementationTest {

    protected abstract NotPrimeFinder getNotPrimeFinder(Integer[] array);

    @Test
    void call_simple() {
        NotPrimeFinder finder = getNotPrimeFinder(new Integer[]{1, 2, 3});
        assertTrue(finder.call());
    }

    @Test
    void call_true() {
        Integer[] array = new Integer[1000];
        Arrays.fill(array, 2147483647);
        array[array.length - 1] = 500;
        NotPrimeFinder finder = getNotPrimeFinder(array);

        assertTrue(finder.call());
    }

    @Test
    void call_false() {
        Integer[] array = new Integer[1000];
        Arrays.fill(array, 2147483647);
        NotPrimeFinder finder = getNotPrimeFinder(array);

        assertFalse(finder.call());
    }

}

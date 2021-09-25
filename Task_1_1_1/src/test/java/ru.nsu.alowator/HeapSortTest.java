package ru.nsu.alowator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class HeapSortTest {
    public HeapSort hs = new HeapSort();
    final public int BIG_ARRAY_SIZE = 1000000;

    @BeforeEach
    void beforeEach() {
        hs = new HeapSort();
    }

    @Test
    void sort_emptyArray() {
        Integer arr[] = {};
        hs.inplaceSort(arr);
        Integer expected[] = {};
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    void sort_simpleArray() {
        Integer arr[] = {1, 3, 5, 4, 2};
        hs.inplaceSort(arr);
        Integer expected[] = {1, 2, 3, 4, 5};
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    void sort_intMaxArray() {
        Integer arr[] = {
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
                Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1
        };
        hs.inplaceSort(arr);
        Integer expected[] = {
                Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE + 1, Integer.MIN_VALUE + 1,
                Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE
        };
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    void sort_bigArray() {
        Integer arr[] = new Integer[BIG_ARRAY_SIZE];
        Integer expected[] = new Integer[BIG_ARRAY_SIZE];

        final int a = 16807, c = 12345, m = 1073741823;
        int x = 1;
        for (int i = 0; i < BIG_ARRAY_SIZE; i++) {
            arr[i] = x;
            expected[i] = x;
            x = (a * x + c) % m;
        }
        hs.inplaceSort(arr);
        Arrays.sort(expected);

        Assertions.assertArrayEquals(expected, arr);
    }

}
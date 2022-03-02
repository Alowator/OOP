package ru.nsu.alowator;


import ru.nsu.alowator.impl.*;

import java.util.Arrays;

public class App {

    final private static int ARRAY_SIZE = 3000;

    public static void main(String[] args) throws Exception {
        Integer[] array = new Integer[ARRAY_SIZE];
        fillArrayWithPrimePositiveNumbers(array);

        NotPrimeFinder[] findersToMeasure = new NotPrimeFinder[]{
                new NotPrimeFinder(array),
                new ParallelStreamNotPrimeFinder(array),
                new ThreadNotPrimeFinder(array, 1),
                new ThreadNotPrimeFinder(array, 2),
                new ThreadNotPrimeFinder(array, 4),
                new ThreadNotPrimeFinder(array, 8),
                new ThreadNotPrimeFinder(array, 16),
        };

        Runner runner = new Runner(findersToMeasure);
        runner.measure();

    }

    private static void fillArrayWithPrimePositiveNumbers(Integer[] array) {
        Arrays.fill(array, 2147483647);
    }

}

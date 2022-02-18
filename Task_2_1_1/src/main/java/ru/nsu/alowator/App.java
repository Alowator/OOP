package ru.nsu.alowator;


import ru.nsu.alowator.impl.*;

public class App {

    final private static int ARRAY_SIZE = 1000000;

    public static void main(String[] args)  {
        Integer[] array = new Integer[ARRAY_SIZE];
        fillArrayWithPrimePositiveNumbers(array);

        NotPrimeFinder[] findersToMeasure = new NotPrimeFinder[]{
                new NotPrimeFinder(array),
                new ParallelStreamNotPrimeFinder(array)
        };

        Runner runner = new Runner(findersToMeasure);
        runner.measure();

    }

    private static void fillArrayWithPrimePositiveNumbers(Integer[] array) {
        int candidate = 2;
        for (int i = 0; i < array.length; i++) {
            while (!Calculus.isPrime(candidate)) {
                candidate++;
                if (candidate < 2)
                    candidate = 2;
            }
            array[i] = candidate;
            candidate++;
            if (candidate < 2)
                candidate = 2;
        }
    }

}

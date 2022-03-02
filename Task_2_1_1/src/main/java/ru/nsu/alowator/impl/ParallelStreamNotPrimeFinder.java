package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

import java.util.Arrays;

public class ParallelStreamNotPrimeFinder extends NotPrimeFinder {

    /**
     * Implementation using StreamAPI (parallel),
     * extends {@link NotPrimeFinder#NotPrimeFinder(Integer[])}
     */
    public ParallelStreamNotPrimeFinder(Integer[] array) {
        super(array);
    }


    /**
     * {@link NotPrimeFinder#call()}
     */
    @Override
    public Boolean call() {
        return !Arrays.stream(array).parallel().allMatch(Calculus::isPrime);
    }
}

package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

import java.util.Arrays;

public class ParallelStreamNotPrimeFinder extends NotPrimeFinder {
    public ParallelStreamNotPrimeFinder(Integer[] array) {
        super(array);
    }

    @Override
    public Boolean call() {
        return !Arrays.stream(array).parallel().allMatch(Calculus::isPrime);
    }
}

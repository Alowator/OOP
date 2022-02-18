package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

import java.util.Arrays;

public class ParallelStreamNotPrimeFinder extends NotPrimeFinder {
    public ParallelStreamNotPrimeFinder(Integer[] array) {
        super(array);
    }

    @Override
    public boolean run() {
        return Arrays.stream(array).parallel().noneMatch(Calculus::isPrime);
    }
}

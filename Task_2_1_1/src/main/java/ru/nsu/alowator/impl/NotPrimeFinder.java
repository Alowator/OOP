package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

public class NotPrimeFinder {

    protected final Integer[] array;

    public NotPrimeFinder(Integer[] array) {
        this.array = array;
    }

    public boolean run() {
        for (Integer x : array) {
            if (!Calculus.isPrime(x))
                return true;
        }
        return false;
    }
}
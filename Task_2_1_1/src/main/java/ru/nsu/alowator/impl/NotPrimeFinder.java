package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

import java.util.concurrent.Callable;

public class NotPrimeFinder implements Callable<Boolean> {

    protected final Integer[] array;

    public NotPrimeFinder(Integer[] array) {
        this.array = array;
    }

    @Override
    public Boolean call() {
        for (Integer x : array) {
            if (!Calculus.isPrime(x))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
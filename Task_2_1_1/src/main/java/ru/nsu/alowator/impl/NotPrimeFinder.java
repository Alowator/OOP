package ru.nsu.alowator.impl;

import ru.nsu.alowator.Calculus;

import java.util.concurrent.Callable;

public class NotPrimeFinder implements Callable<Boolean> {

    protected final Integer[] array;

    /**
     * Base singlethread implementation,
     * other implementations must be inherited from this.
     *
     * @param array of integers, that will be scanned for
     * the presence of non-prime numbers by calling {@code call()}
     */
    public NotPrimeFinder(Integer[] array) {
        this.array = array;
    }

    /**
     * @return {@code true} if contains non-prime numbers
     */
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
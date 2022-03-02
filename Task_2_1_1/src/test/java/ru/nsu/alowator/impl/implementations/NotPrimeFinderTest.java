package ru.nsu.alowator.impl.implementations;


import ru.nsu.alowator.impl.ImplementationTest;
import ru.nsu.alowator.impl.NotPrimeFinder;

class NotPrimeFinderTest extends ImplementationTest {
    @Override
    public NotPrimeFinder getNotPrimeFinder(Integer[] array) {
        return new NotPrimeFinder(array);
    }
}
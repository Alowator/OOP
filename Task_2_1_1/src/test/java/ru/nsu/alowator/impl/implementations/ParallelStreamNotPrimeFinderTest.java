package ru.nsu.alowator.impl.implementations;


import ru.nsu.alowator.impl.ImplementationTest;
import ru.nsu.alowator.impl.NotPrimeFinder;
import ru.nsu.alowator.impl.ParallelStreamNotPrimeFinder;

class ParallelStreamNotPrimeFinderTest extends ImplementationTest {
    @Override
    public NotPrimeFinder getNotPrimeFinder(Integer[] array) {
        return new ParallelStreamNotPrimeFinder(array);
    }
}
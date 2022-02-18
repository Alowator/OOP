package ru.nsu.alowator;

import ru.nsu.alowator.impl.NotPrimeFinder;

public class Runner {
    private final NotPrimeFinder[] findersToMeasure;

    public Runner(NotPrimeFinder[] findersToMeasure) {
        this.findersToMeasure = findersToMeasure;
    }

    public void measure() {
        for (NotPrimeFinder f : findersToMeasure) {
            long time = System.currentTimeMillis();
            boolean res = f.run();
            System.out.print(f.getClass().getSimpleName() + ": ");
            System.out.print((System.currentTimeMillis() - time) + "ms ");
            System.out.println("(" + res + ")");
        }
    }
}

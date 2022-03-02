package ru.nsu.alowator;

import ru.nsu.alowator.impl.NotPrimeFinder;

import java.util.concurrent.Callable;

public class Runner {
    final static int COUNT_OF_PRE_RUNS = 10;
    final static int COUNT_OF_MEASURE_RUNS = 40;

    private final Callable<Boolean>[] findersToMeasure;

    public Runner(NotPrimeFinder[] findersToMeasure) {
        this.findersToMeasure = findersToMeasure;
    }

    public void measure() throws Exception {
        for (Callable<Boolean> f : findersToMeasure) {
            Boolean res = null;

            for (int i = 0; i < COUNT_OF_PRE_RUNS; i++) {
                f.call();
            }

            long time = System.currentTimeMillis();
            for (int i = 0; i < COUNT_OF_MEASURE_RUNS; i++) {
                res = f.call();
            }
            long totalTime = System.currentTimeMillis() - time;

            System.out.print(f + ": ");
            System.out.print(totalTime / COUNT_OF_MEASURE_RUNS + "ms ");
            System.out.println("(" + res + ")");
            System.out.flush();
        }
    }
}

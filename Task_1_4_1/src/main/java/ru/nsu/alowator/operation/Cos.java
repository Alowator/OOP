package ru.nsu.alowator.operation;

import java.util.List;

public class Cos implements Operation {
    @Override
    public int argsCount() {
        return 1;
    }

    @Override
    public double apply(List<Double> args) {
        return Math.cos(args.get(0));
    }
}

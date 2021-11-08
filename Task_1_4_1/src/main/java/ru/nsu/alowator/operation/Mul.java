package ru.nsu.alowator.operation;

import java.util.List;

public class Mul implements Operation {
    @Override
    public int argsCount() {
        return 2;
    }

    @Override
    public double apply(List<Double> args) {
        return args.get(0) * args.get(1);
    }
}

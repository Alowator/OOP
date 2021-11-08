package ru.nsu.alowator.operation;

import java.util.List;

public class Log implements Operation {
    @Override
    public int argsCount() {
        return 1;
    }

    @Override
    public double apply(List<Double> args) {
        return Math.log(args.get(0));
    }
}

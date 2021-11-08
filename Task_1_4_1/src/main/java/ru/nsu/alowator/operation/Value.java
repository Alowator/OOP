package ru.nsu.alowator.operation;

import java.util.List;

public class Value implements Operation {

    private double value;

    public Value(double value) {
        this.value = value;
    }

    @Override
    public int argsCount() {
        return 0;
    }

    @Override
    public double apply(List<Double> args) {
        return value;
    }
}

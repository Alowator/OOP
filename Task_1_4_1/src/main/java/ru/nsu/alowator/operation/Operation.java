package ru.nsu.alowator.operation;

import java.util.List;

public interface Operation {

    int argsCount();
    double apply(List<Double> args);

}

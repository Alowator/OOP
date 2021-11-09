package ru.nsu.alowator;

import ru.nsu.alowator.operation.*;

public class OperationFactory {

    /**
     * Create new operation object by operation name.
     * @param expr operation name
     * @return new operation object
     */
    public static Operation operation(String expr) {
        return switch (expr) {
            case "+" -> new Add();
            case "-" -> new Sub();
            case "*" -> new Mul();
            case "/" -> new Div();
            case "log" -> new Log();
            case "pow" -> new Pow();
            case "sqrt" -> new Sqrt();
            case "sin" -> new Sin();
            case "cos" -> new Cos();
            default -> new Value(Double.parseDouble(expr));
        };
    }

}

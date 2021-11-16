package ru.nsu.alowator;

import ru.nsu.alowator.operation.Operation;

import javax.management.RuntimeErrorException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public final class Calculator {

    /**
     * {@link Calculator#calculate(Scanner)}
     * @param input expression string
     */
    public static double calculate(String input) throws ParseException {
        Scanner scanner = new Scanner(input);
        return calculate(scanner);
    }

    /**
     * Calculate expression result in prefix notation.
     * @param scanner provides expression string
     * @return expression result
     * @throws ParseException if expression is incorrect
     */
    public static double calculate(Scanner scanner) throws ParseException {
        try {
            return nextExpr(scanner);
        } catch (RuntimeException exception) {
            if (Objects.equals(exception.getMessage(), "Not enough arguments")) {
                throw new ParseException("Empty input provided", 0);
            } else {
                throw new ParseException(exception.getMessage(), 1);
            }
        }
    }

    private static double nextExpr(Scanner scanner) throws ParseException {
        String expr;
        if (scanner.hasNext()) {
            expr = scanner.next();
        } else {
            throw new RuntimeException("Not enough arguments");
        }

        Operation operation = OperationFactory.operation(expr);

        List<Double> args = new ArrayList<>();
        for (int i = 0; i < operation.argsCount(); i++) {
            try {
                args.add(nextExpr(scanner));
            } catch (RuntimeException exception) {
                throw new ParseException(exception.getMessage() + " in " + operation, operation.argsCount());
            }

        }

        return operation.apply(args);
    }

}

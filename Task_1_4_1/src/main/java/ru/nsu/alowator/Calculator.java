package ru.nsu.alowator;

import java.io.StringReader;
import java.util.Objects;
import java.util.Scanner;

public class Calculator {

    private Scanner scanner;

    public double calculate(String input) {
        scanner = new Scanner(input);
        return nextExpr();
    }

    private double nextExpr() {
        String expr = scanner.next();
        switch (expr) {
            case "+": return add(nextExpr(), nextExpr());
            case "-": return sub(nextExpr(), nextExpr());
            case "*": return mul(nextExpr(), nextExpr());
            case "/": return div(nextExpr(), nextExpr());
            default: return Double.parseDouble(expr);
        }
    }

    private double add(double a, double b) {
        return a + b;
    }

    private double sub(double a, double b) {
        return a - b;
    }

    private double mul(double a, double b) {
        return a * b;
    }

    private double div(double a, double b) {
        return a / b;
    }

}

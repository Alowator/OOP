package ru.nsu.alowator;

import java.util.Scanner;

public final class Calculator {

    public static double calculate(String input) {
        Scanner scanner = new Scanner(input);
        return nextExpr(scanner);
    }

    public static double calculate(Scanner scanner) {
        return nextExpr(scanner);
    }

    private static double nextExpr(Scanner scanner) {
        String expr = scanner.next();
        return switch (expr) {
            case "+" -> add(nextExpr(scanner), nextExpr(scanner));
            case "-" -> sub(nextExpr(scanner), nextExpr(scanner));
            case "*" -> mul(nextExpr(scanner), nextExpr(scanner));
            case "/" -> div(nextExpr(scanner), nextExpr(scanner));
            case "log" -> log(nextExpr(scanner));
            case "pow" -> pow(nextExpr(scanner), nextExpr(scanner));
            case "sqrt" -> sqrt(nextExpr(scanner));
            case "sin" -> sin(nextExpr(scanner));
            case "cos" -> cos(nextExpr(scanner));
            default -> Double.parseDouble(expr);
        };
    }

    private static double add(double a, double b) {
        return a + b;
    }

    private static double sub(double a, double b) {
        return a - b;
    }

    private static double mul(double a, double b) {
        return a * b;
    }

    private static double div(double a, double b) {
        return a / b;
    }

    private static double log(double a) {
        return Math.log(a);
    }

    private static double pow(double a, double b) {
        return Math.pow(a, b);
    }

    private static double sqrt(double a) {
        return Math.sqrt(a);
    }

    private static double sin(double a) {
        return Math.sin(a);
    }

    private static double cos(double a) {
        return Math.cos(a);
    }

}

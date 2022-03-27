package ru.nsu.alowator.pizzeria;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Order {
    private static int nextId = 0;

    private final int id;
    private final String pizzaName;

    public Order(String pizzaName) {
        this.id = nextId++;
        this.pizzaName = pizzaName;
    }

    public String pizzaName() {
        return pizzaName;
    }

    public int id() {
        return id;
    }

    public String message(String ... status) {
        return String.format(
                "[Order %s] [%s] %s",
                id,
                status[0],
                Arrays.stream(status).skip(1).collect(Collectors.joining(" "))
        );
    }
}

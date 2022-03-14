package ru.nsu.alowator;

import ru.nsu.alowator.pizzeria.Pizzeria;
import ru.nsu.alowator.pizzeria.RealPizzeria;

public class App {
    public static void main(String[] args) {
        Pizzeria pizzeria = new RealPizzeria(10, 5, 50);

        pizzeria.addOrder("pizza1");
        pizzeria.addOrder("pizza2");
        pizzeria.addOrder("pizza3");
        pizzeria.addOrder("pizza4");
        pizzeria.addOrder("pizza5");

    }
}

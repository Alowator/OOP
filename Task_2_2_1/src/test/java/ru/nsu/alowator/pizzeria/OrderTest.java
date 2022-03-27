package ru.nsu.alowator.pizzeria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void pizzaName() {
        String name = "margarita";
        Order order = new Order(name);
        assertEquals(name, order.pizzaName());
    }

    @Test
    void id() {
        Order order1 = new Order("pepperoni");
        Order order2 = new Order("margarita");
        assertNotEquals(order1.id(), order2.id());
    }

    @Test
    void message() {
        String status = "status";
        Order order = new Order("pepperoni");
        assertEquals(
                String.format("[Order %d] [%s] ", order.id(), status),
                order.message(status)
        );
    }
}
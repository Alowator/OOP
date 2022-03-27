package ru.nsu.alowator.pizzeria;

import org.junit.jupiter.api.Test;
import ru.nsu.alowator.storage.entities.BakerEntity;
import ru.nsu.alowator.storage.entities.CourierEntity;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import static java.lang.Thread.sleep;

class PizzeriaTest extends BaseLoggingTest {
    @Test
    void addBaker() throws IOException, InterruptedException {
        BakerEntity baker = new BakerEntity(0, "Mark", 90000);
        pizzeria.addOrder("pizza");

        pizzeria.addBaker(baker);
        sleep(10);

        assertLogContains("[Order 0] [taken]");
    }

    @Test
    void addCourier() throws IOException, InterruptedException {
        BakerEntity baker = new BakerEntity(0, "Mark", 90000);
        CourierEntity courier = new CourierEntity(0, "Mark", 9000);
        pizzeria.addOrder("pizza");
        pizzeria.addBaker(baker);

        pizzeria.addCourier(courier);
        sleep(100);

        assertLogContains("[Order 0] [in delivery]");
    }

    @Test
    void addOrder() throws IOException, InterruptedException {
        BakerEntity baker = new BakerEntity(0, "Mark", 90000);
        CourierEntity courier = new CourierEntity(0, "Mark", 9000);
        pizzeria.addBaker(baker);
        pizzeria.addCourier(courier);

        pizzeria.addOrder("pizza");
        sleep(10);

        assertLogContains("[Order 0] [taken]");
        assertLogContains("[Order 0] [ready]");
        assertLogContains("[Order 0] [in delivery]");
    }

    @Test
    void takeOrder() {
        String name = "pizza";
        pizzeria.addOrder(name);

        Order order = pizzeria.takeOrder();

        assertEquals(name, order.pizzaName());
    }

    @Test
    void addPizza() throws IOException, InterruptedException {
        BakerEntity baker = new BakerEntity(0, "Mark", 90000);
        CourierEntity courier = new CourierEntity(0, "Mark", 9000);
        pizzeria.addBaker(baker);
        pizzeria.addCourier(courier);
        Order order = new Order("pizza");

        pizzeria.addPizza(order);
        sleep(10);

        assertLogContains("[Order 0] [in delivery]");
    }

    @Test
    void info() throws IOException {
        pizzeria.info("kek");
        assertLogContains("kek");
    }
}
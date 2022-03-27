package ru.nsu.alowator.pizzeria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.storage.entities.CourierEntity;

import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest extends BaseLoggingTest {
    Warehouse warehouse;

    @BeforeEach
    void init() {
        warehouse = new Warehouse(100, log);
    }

    @Test
    void addCourier() throws IOException, InterruptedException {
        CourierEntity courier = new CourierEntity(0, "Mark", 100);
        Order order = new Order("pizza");
        warehouse.addPizza(order);

        warehouse.addCourier(courier);
        sleep(100);

        assertLogContains("in delivery");
    }

    @Test
    void addPizza() throws IOException, InterruptedException {
        CourierEntity courier = new CourierEntity(0, "Mark", 100);
        Order order = new Order("pizza");
        warehouse.addCourier(courier);

        warehouse.addPizza(order);
        sleep(10);

        assertLogContains("in delivery");
    }

    @Test
    void takePizza() {
        String name = "pizza";
        warehouse.addPizza(new Order(name));

        Order order = warehouse.takePizza();

        assertEquals(name, order.pizzaName());
    }

    @Test
    void info() throws IOException {
        warehouse.info("kek");
        assertLogContains("kek");
    }
}
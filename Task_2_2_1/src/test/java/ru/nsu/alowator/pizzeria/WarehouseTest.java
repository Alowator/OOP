package ru.nsu.alowator.pizzeria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.storage.entities.CourierEntity;

import java.io.IOException;
import java.util.List;

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
    void takePizzas() {
        warehouse.addPizza(new Order("pizza1"));
        warehouse.addPizza(new Order("pizza2"));

        List<Order> orders = warehouse.takePizzas(2);
        assertEquals("pizza1", orders.get(0).pizzaName());
        assertEquals("pizza2", orders.get(1).pizzaName());
    }

    @Test
    void info() throws IOException {
        warehouse.info("kek");
        assertLogContains("kek");
    }
}
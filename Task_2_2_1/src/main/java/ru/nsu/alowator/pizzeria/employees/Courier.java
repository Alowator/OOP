package ru.nsu.alowator.pizzeria.employees;

import ru.nsu.alowator.pizzeria.Order;
import ru.nsu.alowator.pizzeria.Warehouse;
import ru.nsu.alowator.storage.entities.CourierEntity;

import static java.lang.Thread.sleep;

public class Courier implements Runnable {
    private final Warehouse warehouse;
    private final CourierEntity entity;

    public Courier(Warehouse warehouse, CourierEntity entity) {
        this.warehouse = warehouse;
        this.entity = entity;
    };

    @Override
    public void run() {
        while (true) {
            Order order = warehouse.takePizza();
            ship(order);
        }
    }

    private void ship(Order order) {
        long deliveryTimeMs = Math.round(Math.random() * 10) * 1000;
        warehouse.info(order.message("in delivery", deliveryTimeMs + "ms"));
        try {
            sleep(deliveryTimeMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        warehouse.info(order.message("delivered"));
    }
}

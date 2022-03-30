package ru.nsu.alowator.pizzeria;

import ru.nsu.alowator.pizzeria.employees.Baker;
import ru.nsu.alowator.storage.entities.BakerEntity;
import ru.nsu.alowator.storage.entities.CourierEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class Pizzeria {
    private final Logger log;
    private final Queue<Order> orders;
    private final List<Thread> bakerThreads;

    private final Warehouse warehouse;

    public Pizzeria(int warehouseCapacity, Logger log) {
        this.log = log;
        this.orders = new LinkedList<>();
        this.bakerThreads = new ArrayList<>();
        this.warehouse = new Warehouse(warehouseCapacity, log);
    }

    public void addBaker(BakerEntity entity) {
        Baker baker = new Baker(this, entity);
        Thread bakerThread = new Thread(baker);
        bakerThreads.add(bakerThread);
        bakerThread.start();
    }

    public void addCourier(CourierEntity courierEntity) {
        warehouse.addCourier(courierEntity);
    }

    public void addOrder(String pizzaName) {
        Order order = new Order(pizzaName);
        synchronized (orders) {
            orders.add(order);
            orders.notify();
        }
    }

    public Order takeOrder() {
        synchronized (orders) {
            while (orders.isEmpty()) {
                try {
                    orders.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return orders.poll();
        }
    }

    public void addPizza(Order readyPizza) {
        warehouse.addPizza(readyPizza);
    }

    public void info(String message) {
        log.info(message);
    }
}

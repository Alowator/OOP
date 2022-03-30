package ru.nsu.alowator.pizzeria;

import ru.nsu.alowator.pizzeria.employees.Courier;
import ru.nsu.alowator.storage.entities.CourierEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class Warehouse {
    private final Logger log;
    private final Queue<Order> pizzas;
    private final List<Thread> courierThreads;

    private final int warehouseCapacity;

    public Warehouse(int warehouseCapacity, Logger log) {
        this.log = log;
        this.warehouseCapacity = warehouseCapacity;
        this.courierThreads = new ArrayList<>();
        pizzas = new LinkedList<>();
    }

    public void addCourier(CourierEntity courierEntity) {
        Courier courier = new Courier(this, courierEntity);
        Thread courierThread = new Thread(courier);
        courierThreads.add(courierThread);
        courierThread.start();
    }

    public void addPizza(Order readyPizza) {
        synchronized (pizzas) {
            while (pizzas.size() >= warehouseCapacity) {
                try {
                    pizzas.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pizzas.add(readyPizza);
            pizzas.notify();
        }
    }

    public Order takePizza() {
        synchronized (pizzas) {
            while (pizzas.isEmpty()) {
                try {
                    pizzas.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pizzas.notify();
            return pizzas.poll();
        }
    }

    public void info(String message) {
        log.info(message);
    }
}

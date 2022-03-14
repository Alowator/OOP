package ru.nsu.alowator.pizzeria;

import ru.nsu.alowator.pizzeria.employees.Baker;
import ru.nsu.alowator.pizzeria.employees.Courier;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RealPizzeria implements Pizzeria {
    final private List<Baker> bakers;

    final private Queue<Order> orders;

    final private Warehouse warehouse;

    public RealPizzeria(int bakersCount, int couriersCount, int warehouseCapacity) {
        this.orders = new LinkedList<>();
        this.warehouse = new Warehouse(warehouseCapacity);

        bakers = new ArrayList<>();
        for (int i = 0; i < bakersCount; i++) {
            Baker baker = new Baker(this);
            baker.start();
            bakers.add(baker);
        }
    }

    @Override
    public void addOrder(String pizzaName) {
        Order order = new Order(pizzaName);
        synchronized (this.orders) {
            this.orders.add(order);
        }
    }

    public Order getOrder() {
        synchronized (this.orders) {
            if (orders.isEmpty())
                return null;
            else
                return orders.poll();
        }
    }
}

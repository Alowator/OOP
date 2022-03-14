package ru.nsu.alowator.pizzeria.employees;

import ru.nsu.alowator.pizzeria.Order;
import ru.nsu.alowator.pizzeria.RealPizzeria;

public class Baker extends Thread {
    final private RealPizzeria pizzeria;

    public Baker(RealPizzeria pizzeria) {
        this.pizzeria = pizzeria;
    };

    @Override
    public void run() {
        while (true) {
            Order order = pizzeria.getOrder();
            if (order != null) {
                bake(order);
                System.out.println("Pizza " + order.pizzaName() + " ready.");
            }

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void bake(Order order) {
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

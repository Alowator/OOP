package ru.nsu.alowator.pizzeria.employees;

import ru.nsu.alowator.pizzeria.Order;
import ru.nsu.alowator.pizzeria.Pizzeria;
import ru.nsu.alowator.storage.entities.BakerEntity;

import static java.lang.Thread.sleep;

public class Baker implements Runnable {
    private final Pizzeria pizzeria;
    private final BakerEntity entity;

    public Baker(Pizzeria pizzeria, BakerEntity entity) {
        this.pizzeria = pizzeria;
        this.entity = entity;
    };

    @Override
    public void run() {
        while (true) {
            Order order = pizzeria.takeOrder();
            bake(order);
            pizzeria.addPizza(order);
        }
    }

    private void bake(Order order) {
        long bakeTime = 10000 / entity.getSkill();
        pizzeria.info(order.message("taken", bakeTime + "ms"));
        try {
            sleep(bakeTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pizzeria.info(order.message("ready"));
    }
}

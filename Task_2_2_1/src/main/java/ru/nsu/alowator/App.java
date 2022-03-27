package ru.nsu.alowator;

import ru.nsu.alowator.pizzeria.Pizzeria;
import ru.nsu.alowator.storage.Storage;
import ru.nsu.alowator.storage.entities.BakerEntity;
import ru.nsu.alowator.storage.entities.CourierEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class App {
    public static void main(String[] args) throws IOException {
        Logger log = createLogFromProperties("pizzeriaLogger");

        Pizzeria pizzeria = new Pizzeria(  3, log);

        List<BakerEntity> bakers = Storage.bakers().findAll();
        List<CourierEntity> couriers = Storage.couriers().findAll();
        bakers.forEach(pizzeria::addBaker);
        couriers.forEach(pizzeria::addCourier);

        pizzeria.addOrder("pizza0");
        pizzeria.addOrder("pizza1");
        pizzeria.addOrder("pizza2");
        pizzeria.addOrder("pizza3");
    }

    public static Logger createLogFromProperties(String loggerName) throws IOException {
        InputStream stream = App.class.getClassLoader().getResourceAsStream("logging.properties");
        LogManager.getLogManager().readConfiguration(stream);
        return Logger.getLogger(loggerName);
    }
}

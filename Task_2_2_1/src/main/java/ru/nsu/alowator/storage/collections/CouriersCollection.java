package ru.nsu.alowator.storage.collections;

import ru.nsu.alowator.storage.entities.CourierEntity;

import java.util.List;

public interface CouriersCollection {
    CourierEntity createCourier(String name, int trunkSize);
    List<CourierEntity> findAll();
}

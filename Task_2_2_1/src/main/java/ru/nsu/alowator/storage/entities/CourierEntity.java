package ru.nsu.alowator.storage.entities;

public class CourierEntity {
    private final int id;
    private final String name;
    private final int trunkSize;

    public CourierEntity(int id, String name, int trunkSize) {
        this.id = id;
        this.name = name;
        this.trunkSize = trunkSize;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTrunkSize() {
        return trunkSize;
    }
}

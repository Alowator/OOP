package ru.nsu.alowator.storage.entities;

public class BakerEntity {
    private final int id;
    private final String name;
    private final int skill;

    public BakerEntity(int id, String name, int skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSkill() {
        return skill;
    }
}

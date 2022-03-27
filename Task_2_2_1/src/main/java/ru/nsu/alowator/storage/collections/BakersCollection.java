package ru.nsu.alowator.storage.collections;

import ru.nsu.alowator.storage.entities.BakerEntity;

import java.util.List;

public interface BakersCollection {
    BakerEntity createBaker(String name, int skill);
    List<BakerEntity> findAll();
}

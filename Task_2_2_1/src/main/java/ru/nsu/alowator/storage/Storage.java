package ru.nsu.alowator.storage;

import ru.nsu.alowator.storage.collections.BakersCollection;
import ru.nsu.alowator.storage.collections.CouriersCollection;
import ru.nsu.alowator.storage.impl.json.JsonBakersCollection;
import ru.nsu.alowator.storage.impl.json.JsonCouriersCollection;

public class Storage {
    public static BakersCollection bakers() {
        return new JsonBakersCollection();
    }

    public static CouriersCollection couriers() {
        return new JsonCouriersCollection();
    }
}

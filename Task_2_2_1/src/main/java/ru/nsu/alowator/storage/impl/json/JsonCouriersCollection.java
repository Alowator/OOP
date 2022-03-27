package ru.nsu.alowator.storage.impl.json;

import ru.nsu.alowator.storage.collections.CouriersCollection;
import ru.nsu.alowator.storage.entities.CourierEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonCouriersCollection extends BaseCollection implements CouriersCollection {
    private static final String FILENAME = "json/couriers.txt";

    @Override
    public CourierEntity createCourier(String name, int skill) {
        int maxId = -1;
        List<CourierEntity> bakers = getCouriers();
        for (var b : bakers) {
            maxId = Math.max(maxId, b.getId());
        }
        CourierEntity baker = new CourierEntity(maxId + 1, name, skill);
        bakers.add(baker);

        setCouriers(bakers);
        return baker;
    }

    @Override
    public List<CourierEntity> findAll() {
        return getCouriers();
    }

    private List<CourierEntity> getCouriers() {
        String dump = readFile(FILENAME);
        List<CourierEntity> list = Arrays.asList(gson().fromJson(dump, CourierEntity[].class));
        return new ArrayList<>(list);
    }

    private void setCouriers(List<CourierEntity> bakers) {
        String dump = gson().toJson(bakers);
        writeFile(FILENAME, dump);
    }
}

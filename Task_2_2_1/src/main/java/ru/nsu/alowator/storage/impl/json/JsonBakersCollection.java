package ru.nsu.alowator.storage.impl.json;

import ru.nsu.alowator.storage.collections.BakersCollection;
import ru.nsu.alowator.storage.entities.BakerEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonBakersCollection extends BaseCollection implements BakersCollection {
    private static final String FILENAME = "json/bakers.txt";

    @Override
    public BakerEntity createBaker(String name, int skill) {
        int maxId = -1;
        List<BakerEntity> bakers = getBakers();
        for (var b : bakers) {
            maxId = Math.max(maxId, b.getId());
        }
        BakerEntity baker = new BakerEntity(maxId + 1, name, skill);
        bakers.add(baker);

        setBakers(bakers);
        return baker;
    }

    @Override
    public List<BakerEntity> findAll() {
        return getBakers();
    }

    private List<BakerEntity> getBakers() {
        String dump = readFile(FILENAME);
        List<BakerEntity> list = Arrays.asList(gson().fromJson(dump, BakerEntity[].class));
        return new ArrayList<>(list);
    }

    private void setBakers(List<BakerEntity> bakers) {
        String dump = gson().toJson(bakers);
        writeFile(FILENAME, dump);
    }
}

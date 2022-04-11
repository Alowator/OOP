package ru.nsu.alowator.core;

public class Game {
    private final int DEFAULT_RAW_COUNT = 15;
    private final int DEFAULT_COL_COUNT = 20;

    private final GameWatcher watcher;
    private final Grid grid;

    public Game(GameWatcher watcher) {
        this.grid = new Grid(DEFAULT_RAW_COUNT, DEFAULT_COL_COUNT);
        this.watcher = watcher;
    }
}

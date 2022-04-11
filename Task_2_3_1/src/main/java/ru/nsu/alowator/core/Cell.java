package ru.nsu.alowator.core;

public class Cell {
    private final int raw;
    private final int col;
    private Item item;

    enum Item {
        EMPTY,
        FOOD,
        WALL,
    }

    public Cell(int raw, int col) {
        this.raw = raw;
        this.col = col;
        item = Item.EMPTY;
    }

    public Item contains() {
        return item;
    }
}

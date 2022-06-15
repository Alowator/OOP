package ru.nsu.alowator.core.entities;

public class Cell {
    private final int row;
    private final int col;
    private final Type type;

    public enum Type {
        EMPTY,
        FOOD,
        WALL,
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.type = Type.EMPTY;
    }

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public boolean isIntersect(Cell cell) {
        return row == cell.row && col == cell.col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Type getType() {
        return type;
    }
}

package ru.nsu.alowator.core;

public class Grid {
    private final int rawCount;
    private final int colCount;
    private final Cell[][] cells;

    public Grid(int rawCount, int colCount) {
        this.rawCount = rawCount;
        this.colCount = colCount;

        cells = new Cell[rawCount][colCount];
        for (int row = 0; row < rawCount; row++) {
            for (int column = 0; column < colCount; column++) {
                cells[row][column] = new Cell(row, column);
            }
        }
    }
}

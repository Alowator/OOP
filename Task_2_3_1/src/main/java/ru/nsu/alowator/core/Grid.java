package ru.nsu.alowator.core;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class Grid {
    private final int rowCount;
    private final int colCount;
    private final Cell[][] cells;
    private final List<Cell> emptyCells;

    public Grid(int rowCount, int colCount, int foodCount, int wallCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;

        emptyCells = new LinkedList<>();
        cells = new Cell[rowCount][colCount];
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < colCount; column++) {
                Cell newCell = new Cell(row, column);
                cells[row][column] = newCell;
                emptyCells.add(newCell);
            }
        }

        addFood(foodCount);
        addWall(wallCount);
    }

    public void addFood(int foodCount) {
        while (foodCount > 0 && !emptyCells.isEmpty()) {
            Cell nextFoodCell = emptyCells.remove(ThreadLocalRandom.current().nextInt(emptyCells.size()));
            exchangeCellType(nextFoodCell.getRow(), nextFoodCell.getCol(), Cell.Type.FOOD);
            foodCount -= 1;
        }
    }

    public void addWall(int wallCount) {
        while (wallCount > 0 && !emptyCells.isEmpty()) {
            int wallCellsCount = 5;
            Cell nextWalCell = emptyCells.remove(ThreadLocalRandom.current().nextInt(emptyCells.size()));
            exchangeCellType(nextWalCell.getRow(), nextWalCell.getCol(), Cell.Type.WALL);
            addWallPiece(nextWalCell, wallCellsCount - 1);
            wallCount -= 1;
        }
    }

    private void addWallPiece(Cell wall, int count) {
//        while (count > 0) {
//            List<Integer[]> diffs = new ArrayList<>(){{1, 1}, {1, 0}};
//            Collections.shuffle(diffs);
//            count -= 1;
//        }
    }

    public void exchangeCellType(int row, int col, Cell.Type type) {
        Cell cell = cells[row][col];
        if (cell.getType() == Cell.Type.EMPTY)
            emptyCells.remove(cell);

        Cell newCell = new Cell(row, col, type);
        cells[row][col] = newCell;
        if (newCell.getType() == Cell.Type.EMPTY)
            emptyCells.add(newCell);
    }

    public int getColCount() {
        return colCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public Stream<Cell> stream() {
        return Arrays.stream(cells).flatMap(Arrays::stream);
    }
}

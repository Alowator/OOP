package ru.nsu.alowator.core.entities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void addFood() {
        Grid grid = new Grid(10, 10, 0, 0);

        int addFoodCount = 3;
        grid.addFood(addFoodCount);

        long resFoodCount = grid.stream().filter(cell -> cell.getType() == Cell.Type.FOOD).count();
        assertEquals(addFoodCount, resFoodCount);
    }

    @Test
    void addWall() {
        Grid grid = new Grid(10, 10, 0, 0);

        int addWallCount = 3;
        grid.addWall(addWallCount);

        long resWallCount = grid.stream().filter(cell -> cell.getType() == Cell.Type.WALL).count();
        assertEquals(addWallCount, resWallCount);
    }

    @Test
    void exchangeCellType() {
        Grid grid = new Grid(10, 10, 0, 10 * 10);

        grid.exchangeCellType(0, 0, Cell.Type.EMPTY);

        assertEquals(Cell.Type.EMPTY, grid.getCell(0, 0).getType());
    }

    @Test
    void getRandomFreeCell() {
        Grid grid = new Grid(10, 10, 0, 10 * 10);

        assertThrows(RuntimeException.class, grid::getRandomFreeCell);
    }

    @Test
    void getColCount() {
        Grid grid = new Grid(10, 5, 0, 0);
        assertEquals(5, grid.getColCount());
    }

    @Test
    void getRowCount() {
        Grid grid = new Grid(10, 5, 0, 0);
        assertEquals(10, grid.getRowCount());
    }

    @Test
    void getCell() {
        Grid grid = new Grid(10, 5, 0, 0);
        assertEquals(Cell.Type.EMPTY, grid.getCell(0, 0).getType());
    }

    @Test
    void stream() {
        Grid grid = new Grid(10, 5, 0, 0);
        assertEquals(10 * 5, grid.stream().count());
    }

    @Test
    void adjacentCells() {
        Grid grid = new Grid(10, 10, 0, 0);

        Cell main = grid.getCell(2, 2);
        Cell a = grid.getCell(1, 2);
        Cell b = grid.getCell(3, 2);
        Cell c = grid.getCell(2, 1);
        Cell d = grid.getCell(2, 3);
        List<Cell> cells = grid.adjacentCells(main);

        assertArrayEquals(new Cell[]{a, b, c, d}, cells.toArray());
    }
}
package ru.nsu.alowator.core.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void isIntersectTrue() {
        Cell a = new Cell(1, 1);
        Cell b = new Cell(1, 1);
        assertTrue(a.isIntersect(b));
    }

    @Test
    void isIntersectFalse() {
        Cell a = new Cell(1, 0);
        Cell b = new Cell(1, 1);
        assertFalse(a.isIntersect(b));
    }

    @Test
    void getRow() {
        Cell cell = new Cell(1, 0);
        assertEquals(1, cell.getRow());
    }

    @Test
    void getCol() {
        Cell cell = new Cell(1, 0);
        assertEquals(0, cell.getCol());
    }

    @Test
    void getType() {
        Cell cell = new Cell(1, 0, Cell.Type.WALL);
        assertEquals(Cell.Type.WALL, cell.getType());
    }
}
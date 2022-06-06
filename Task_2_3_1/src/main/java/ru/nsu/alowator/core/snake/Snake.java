package ru.nsu.alowator.core.snake;

import ru.nsu.alowator.core.Cell;
import ru.nsu.alowator.core.Grid;

import java.util.LinkedList;
import java.util.stream.Stream;

public class Snake {
    private final Grid grid;
    private final LinkedList<Cell> snakeBody = new LinkedList<>();
    private Direction direction;
    private Direction directionOrder;
    private int growthPotential;


    public Snake(Grid grid) {
        this.grid = grid;
        snakeBody.add(grid.getRandomFreeCell());
        direction = Direction.RIGHT;
        growthPotential = 0;
        directionOrder = null;
    }

    public void move() {
        if (directionOrder != null) {
            direction = directionOrder;
            directionOrder = null;
        }

        int rowCount = grid.getRowCount();
        int colCount = grid.getColCount();

        int newRow = snakeBody.getLast().getRow();
        int newCol = snakeBody.getLast().getCol();
        switch (direction) {
            case UP -> newRow = (rowCount + newRow - 1) % rowCount;
            case RIGHT -> newCol = (newCol + 1) % colCount;
            case DOWN -> newRow = (newRow + 1) % rowCount;
            case LEFT -> newCol = (colCount + newCol - 1) % colCount;
        }

        snakeBody.add(grid.getCell(newRow, newCol));
        if (growthPotential > 0)
            growthPotential -= 1;
        else
            snakeBody.removeFirst();
    }

    public void turn(Direction newDirection) {
        if (direction.opposite() != newDirection)
            directionOrder = newDirection;
    }

    public void grow() {
        growthPotential += 1;
    }

    public Cell head() {
        return snakeBody.getLast();
    }

    public int size() {
        return snakeBody.size();
    }

    public Stream<Cell> stream() {
        return snakeBody.stream();
    }
}

package ru.nsu.alowator.core.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    Grid grid;
    Snake snake;

    @BeforeEach
    void init() {
        grid = new Grid(10, 10, 0, 0);
        snake = new Snake(grid);
    }

    @Test
    void move() {
        Cell start = snake.head();
        snake.move();

        assertTrue(grid.adjacentCells(start).contains(snake.head()));
    }

    @Test
    void turn() {
        snake.turn(Direction.RIGHT);
        snake.move();
        snake.turn(Direction.RIGHT);
        snake.move();
    }

    @Test
    void grow() {
        snake.grow();
        snake.move();

        assertEquals(2, snake.size());
    }

    @Test
    void head() {
        Cell start = snake.head();
        snake.grow();
        snake.move();

        assertTrue(grid.adjacentCells(start).contains(snake.head()));
    }

    @Test
    void size() {
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();

        assertEquals(3, snake.size());
    }

    @Test
    void stream() {
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();

        assertEquals(3, snake.stream().count());
    }

    @Test
    void cut() {
        snake.grow();
        snake.move();
        Cell cutCell = snake.head();
        snake.grow();
        snake.move();

        snake.cut(cutCell);

        assertEquals(1, snake.size());
    }
}
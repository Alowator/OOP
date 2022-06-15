package ru.nsu.alowator.core.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.alowator.core.entities.Enemies;
import ru.nsu.alowator.core.entities.Grid;
import ru.nsu.alowator.core.entities.Snake;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    Grid grid;
    Snake snake;
    Enemies enemies;
    Status status;

    Frame frame;

    @BeforeEach
    void init() {
        grid = new Grid(10, 10, 0, 0);
        snake = new Snake(grid);
        enemies = new Enemies(grid, 2);
        status = Status.PLAYING;
        frame = new Frame(grid, snake, enemies.stream().toList(), status);
    }

    @Test
    void grid() {
        assertEquals(grid, frame.grid());
    }

    @Test
    void snake() {
        assertEquals(snake, frame.snake());
    }

    @Test
    void enemies() {
        assertEquals(enemies.stream().toList(), frame.enemies());
    }

    @Test
    void status() {
        assertEquals(status, frame.status());
    }
}
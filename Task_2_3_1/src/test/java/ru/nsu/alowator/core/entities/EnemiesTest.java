package ru.nsu.alowator.core.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemiesTest {

    @Test
    void updateStrategy() {
        Grid grid = new Grid(10, 10, 1, 0);
        Enemies enemies = new Enemies(grid, 1);
        Snake player = new Snake(grid);

        for (int i = 0; i < grid.getColCount() * grid.getRowCount(); i++) {
            enemies.updateStrategy(player);
            enemies.stream().forEach(Snake::move);
            enemies.stream().forEach(snake -> {
                if (snake.head().getType() == Cell.Type.FOOD)
                    snake.grow();
            });
        }

        assertTrue(enemies.stream().anyMatch(enemy -> enemy.size() > 1));
    }

    @Test
    void remove() {
        Grid grid = new Grid(5, 5, 1, 0);
        Enemies enemies = new Enemies(grid, 10);

        enemies.stream().forEach(enemies::remove);
        assertEquals(0, enemies.stream().count());
    }

    @Test
    void stream() {
        Grid grid = new Grid(5, 5, 1, 0);
        Enemies enemies = new Enemies(grid, 10);

        assertEquals(10, enemies.stream().count());
    }
}
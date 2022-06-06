package ru.nsu.alowator.core;

import ru.nsu.alowator.core.snake.Snake;

import java.util.List;

public interface GameWatcher {
    void updateFrame(Grid grid, Snake snake, List<Snake> enemies);
    void gameOver();
    void gameWin();
}

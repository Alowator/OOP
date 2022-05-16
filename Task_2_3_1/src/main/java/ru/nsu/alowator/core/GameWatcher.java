package ru.nsu.alowator.core;

public interface GameWatcher {
    void updateFrame(Grid grid, Snake snake);
    void gameOver();
    void gameWin();
}

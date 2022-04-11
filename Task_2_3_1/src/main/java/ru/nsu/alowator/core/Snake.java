package ru.nsu.alowator.core;

import java.util.LinkedList;

public class Snake {
    private LinkedList<Cell> snakeBody = new LinkedList<>();

    public Snake(Cell startCell)
    {
        snakeBody.add(startCell);
    }
}

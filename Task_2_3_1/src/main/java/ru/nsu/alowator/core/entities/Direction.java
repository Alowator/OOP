package ru.nsu.alowator.core.entities;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case RIGHT -> LEFT;
            case DOWN -> UP;
            case LEFT -> RIGHT;
        };
    }
}

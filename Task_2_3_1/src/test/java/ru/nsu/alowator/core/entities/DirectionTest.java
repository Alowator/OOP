package ru.nsu.alowator.core.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @Test
    void oppositeUp() {
        Direction up = Direction.UP;
        assertEquals(Direction.DOWN, up.opposite());
    }

    @Test
    void oppositeRight() {
        Direction right = Direction.RIGHT;
        assertEquals(Direction.LEFT, right.opposite());
    }

    @Test
    void oppositeDown() {
        Direction down = Direction.DOWN;
        assertEquals(Direction.UP, down.opposite());
    }

    @Test
    void oppositeLeft() {
        Direction left = Direction.LEFT;
        assertEquals(Direction.RIGHT, left.opposite());
    }
}
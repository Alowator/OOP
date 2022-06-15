package ru.nsu.alowator.core;

import org.junit.jupiter.api.Test;
import ru.nsu.alowator.core.entities.Direction;
import ru.nsu.alowator.core.frame.Frame;
import ru.nsu.alowator.view.Paint;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    class ViewMock implements View {
        Frame lastFrame = null;

        @Override
        public void updateFrame(Frame frame) {
            lastFrame = frame;
        }
    }

    @Test
    void nextFrame() {
        ViewMock view = new ViewMock();
        Game game = new Game(view, 10, 10, 0, 0, 10, 3);

        game.nextFrame();

        assertNotNull(view.lastFrame);
    }

    @Test
    void turn() {
        ViewMock view = new ViewMock();
        Game game = new Game(view, 10, 10, 0, 0, 10, 3);

        game.turn(Direction.UP);
        game.nextFrame();
        game.nextFrame();
    }
}
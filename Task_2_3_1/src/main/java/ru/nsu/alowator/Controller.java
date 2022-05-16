package ru.nsu.alowator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.nsu.alowator.core.*;


public class Controller {
    private Paint paint;
    private Game game = null;

    @FXML private Canvas canvas;

    @FXML
    private void newAction(ActionEvent event) {
        if (game != null)
            game.end();

        paint = new Paint(canvas);
        game = new Game(paint);
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void keyHandler(KeyEvent e) {
        if (e.getCode() == KeyCode.RIGHT) {
            game.turn(Direction.RIGHT);
        }
        else if(e.getCode() == KeyCode.UP) {
            game.turn(Direction.UP);
        }
        else if(e.getCode() == KeyCode.LEFT) {
            game.turn(Direction.LEFT);
        }
        else if(e.getCode() == KeyCode.DOWN) {
            game.turn(Direction.DOWN);
        }
    }
}

package ru.nsu.alowator.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.nsu.alowator.Application;
import ru.nsu.alowator.core.entities.Direction;
import ru.nsu.alowator.view.Paint;
import ru.nsu.alowator.core.*;

import java.io.IOException;

import static javafx.animation.Animation.INDEFINITE;


public class Main {
    private static final double FRAME_TIME_MILLIS = 175;

    @FXML public BorderPane rootPane;
    @FXML public HBox mainBox;
    @FXML public Canvas canvas;

    private Game game;
    private Timeline timeline = null;

    private final Scene settingsScene;
    private final Settings settings;

    public Main() throws IOException {
        FXMLLoader settingsFxmlLoader = new FXMLLoader(Application.class.getResource("settings-view.fxml"));
        settingsScene = new Scene(settingsFxmlLoader.load());
        settings = settingsFxmlLoader.getController();
    }

    @FXML
    private void newGameAction(ActionEvent event) {
        if (timeline != null)
            timeline.stop();

        Paint paint = new Paint(canvas);
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.setWidth(paint.getCellSize() * settings.getColCount() + 15);
        stage.setHeight(paint.getCellSize() * settings.getRowCount() + 70);

        game = new Game(paint,
            settings.getRowCount(),
            settings.getColCount(),
            settings.getFoodCount(),
            settings.getWallCount(),
            settings.getWinSize(),
            settings.getEnemiesCount()
        );

        timeline = new Timeline(new KeyFrame(Duration.millis(FRAME_TIME_MILLIS), a -> game.nextFrame()));
        timeline.setCycleCount(INDEFINITE);
        timeline.play();
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void settingsAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(settingsScene);
        stage.show();
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

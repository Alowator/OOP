package ru.nsu.alowator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Settings {
    @FXML public BorderPane rootPane;
    @FXML public TextField rowTextField;
    @FXML public TextField colTextField;
    @FXML public TextField foodTextField;
    @FXML public TextField wallTextField;
    @FXML public TextField winScoreTextField;
    @FXML public TextField enemiesTextField;

    // Default values
    private int rowCount = 40;
    private int colCount = 40;
    private int foodCount = 30;
    private int wallCount = 30;
    private int enemiesCount = 5;
    private int winSize = 25;

    public void initialize() {
        rowTextField.setText(String.valueOf(rowCount));
        colTextField.setText(String.valueOf(colCount));
        foodTextField.setText(String.valueOf(foodCount));
        wallTextField.setText(String.valueOf(wallCount));
        enemiesTextField.setText(String.valueOf(enemiesCount));
        winScoreTextField.setText(String.valueOf(winSize));
    }

    public void save(ActionEvent actionEvent) {
        rowCount = Integer.parseInt(rowTextField.getText().replaceAll("[^\\d]", ""));
        colCount = Integer.parseInt(colTextField.getText().replaceAll("[^\\d]", ""));
        foodCount = Integer.parseInt(foodTextField.getText().replaceAll("[^\\d]", ""));
        wallCount = Integer.parseInt(wallTextField.getText().replaceAll("[^\\d]", ""));
        enemiesCount = Integer.parseInt(enemiesTextField.getText().replaceAll("[^\\d]", ""));
        winSize = Integer.parseInt(winScoreTextField.getText().replaceAll("[^\\d]", ""));
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getWallCount() {
        return wallCount;
    }

    public int getWinSize() {
        return winSize;
    }

    public int getEnemiesCount() {
        return enemiesCount;
    }
}

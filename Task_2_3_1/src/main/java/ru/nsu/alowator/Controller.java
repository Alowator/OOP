package ru.nsu.alowator;

import javafx.event.ActionEvent;
import ru.nsu.alowator.core.Game;
import ru.nsu.alowator.core.GameWatcher;
import ru.nsu.alowator.core.Grid;

public class Controller implements GameWatcher {
    private Game game;

    @Override
    public void updateGrid(Grid grid) {

    }

    public void onStartButtonClick(ActionEvent actionEvent) {
        game = new Game(this);
    }
}
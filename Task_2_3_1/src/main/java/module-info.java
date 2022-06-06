module ru.nsu.alowator {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.alowator to javafx.fxml;
    exports ru.nsu.alowator;
    exports ru.nsu.alowator.controller;
    opens ru.nsu.alowator.controller to javafx.fxml;
    exports ru.nsu.alowator.view;
    opens ru.nsu.alowator.view to javafx.fxml;
    exports ru.nsu.alowator.core;
    opens ru.nsu.alowator.core to javafx.fxml;
}
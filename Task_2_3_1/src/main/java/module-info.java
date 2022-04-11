module ru.nsu.alowator {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.nsu.alowator to javafx.fxml;
    exports ru.nsu.alowator;
}
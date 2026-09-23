module Main {
    requires javafx.controls;
    requires javafx.fxml;

    // Allow JavaFX to access classes inside these packages via reflection
    opens Main to javafx.fxml;
    opens controllers to javafx.fxml;
    opens models to javafx.fxml;

    // Export packages so the app can use them normally
    exports Main;
    exports controllers;
    exports models;
}

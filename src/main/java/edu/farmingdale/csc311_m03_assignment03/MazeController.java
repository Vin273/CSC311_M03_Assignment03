package edu.farmingdale.csc311_m03_assignment03;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MazeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        if (welcomeText != null) {
            welcomeText.setText("Welcome to JavaFX Application!");
        }
    }
}
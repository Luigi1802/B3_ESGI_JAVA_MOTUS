package fr.esgi.controller;

import java.io.IOException;

import fr.esgi.App;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class MenuController {
    @FXML
    private void switchToGrilles() throws IOException {
        App.setRoot("grilles");
    }

    @FXML
    private void switchToNomDevs() throws IOException {
        App.setRoot("nomDevs");
    }

    // DEV
    @FXML
    private void switchToResumePartie() throws IOException {
        App.setRoot("resumePartie");
    }

}

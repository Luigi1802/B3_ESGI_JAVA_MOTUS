package fr.esgi.controller;

import fr.esgi.App;
import javafx.fxml.FXML;

import java.io.IOException;

public class ResumePartieController {
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}

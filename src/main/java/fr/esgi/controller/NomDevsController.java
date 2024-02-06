package fr.esgi.controller;

import java.io.IOException;

import fr.esgi.App;
import javafx.fxml.FXML;

public class NomDevsController {
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }
}

package fr.esgi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.esgi.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class NomDevsController implements Initializable {

    @FXML
    private Button menu;
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    /**
     * Cette méthode sert à initialiser la fenêtre FXML et préparer les interaction possibles pour les utilisateurs.
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Survol bouttons menu
        menu.setOnMouseEntered(event -> menu.setOpacity(0.5));
        menu.setOnMouseExited(event -> menu.setOpacity(1));

    }
}

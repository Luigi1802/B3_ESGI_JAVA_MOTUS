package fr.esgi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.esgi.App;
import fr.esgi.service.PartieService;
import fr.esgi.service.impl.PartieServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;

public class MenuController implements Initializable {
    private static PartieService partieService = new PartieServiceImpl();

    @FXML
    private Button boutonPartie;
    @FXML
    private Button boutonNomDevs;

    @FXML
    private void switchToGrilles() throws IOException {
        // Lancement de la partie
        partieService.lancerNouvellePartie();
    }

    @FXML
    private void switchToNomDevs() throws IOException {
        App.setRoot("nomDevs");
    }

    /**
     * Cette méthode sert à initialiser la fenêtre FXML et préparer les interaction possibles pour les utilisateurs.
     * @param location
     * @param resources
     */
    public void initialize(URL location, ResourceBundle resources) {
        //Survol bouttons menu
        boutonPartie.setOnMouseEntered(event -> boutonPartie.setOpacity(0.5));
        boutonPartie.setOnMouseExited(event -> boutonPartie.setOpacity(1));

        boutonNomDevs.setOnMouseEntered(event -> boutonNomDevs.setOpacity(0.5));
        boutonNomDevs.setOnMouseExited(event -> boutonNomDevs.setOpacity(1));

    }

}

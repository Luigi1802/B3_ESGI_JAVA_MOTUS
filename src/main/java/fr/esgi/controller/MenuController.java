package fr.esgi.controller;

import java.io.IOException;

import fr.esgi.App;
import fr.esgi.service.PartieService;
import fr.esgi.service.impl.PartieServiceImpl;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

public class MenuController {

    private static PartieService partieService = new PartieServiceImpl();
    @FXML
    private void switchToGrilles() throws IOException {
        // Lancement de la partie
        partieService.lancerNouvellePartie();
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

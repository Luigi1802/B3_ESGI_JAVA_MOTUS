package fr.esgi.controller;

import fr.esgi.App;
import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import fr.esgi.service.PartieService;
import fr.esgi.service.impl.PartieServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResumePartieController implements Initializable {
    private static PartieService partieService = new PartieServiceImpl();

    // Boutons
    @FXML
    private Button menu, boutonTriSurLeMot, boutonTriSurLeTemps;

    // Affichage état de la partie
    @FXML
    private Label labelVictoirePartie;
    // Bloc de manche
    @FXML
    private Pane paneManche1, paneManche2, paneManche3, paneManche4;
    // Affichage état de la manche
    @FXML
    Label labelVictoireManche1, labelVictoireManche2, labelVictoireManche3, labelVictoireManche4;
    // Affichage mot à trouver manche
    @FXML
    Label labelMotATrouverManche1, labelMotATrouverManche2, labelMotATrouverManche3, labelMotATrouverManche4;
    // Affichage nombre d'éssai manche
    @FXML
    Label labelNombreEssaiManche1, labelNombreEssaiManche2, labelNombreEssaiManche3, labelNombreEssaiManche4;
    // Affichage temps passé manche
    @FXML
    Label tempsPasseManche1, tempsPasseManche2, tempsPasseManche3, tempsPasseManche4;

    // Liste de manche jouées
    private static ArrayList<Manche> manchesJouees = new ArrayList<Manche>();

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Hover sur boutons
        menu.setOnMouseEntered(event -> menu.setOpacity(0.5));
        menu.setOnMouseExited(event -> menu.setOpacity(1));
        boutonTriSurLeMot.setOnMouseEntered(event -> boutonTriSurLeMot.setOpacity(0.5));
        boutonTriSurLeMot.setOnMouseExited(event -> boutonTriSurLeMot.setOpacity(1));
        boutonTriSurLeTemps.setOnMouseEntered(event -> boutonTriSurLeTemps.setOpacity(0.5));
        boutonTriSurLeTemps.setOnMouseExited(event -> boutonTriSurLeTemps.setOpacity(1));

        // Etat de la partie
        statuerEtatPartie();
        // Remplir les résumés de manche
        resumerManches();

        // DEV LOG
        System.out.println(partieService.getPartie().getManches().get(0));

    }

    public void statuerEtatPartie() {
        if (partieService.getPartie().isVictoire()) {
            labelVictoirePartie.setText("Partie gagnée!");
            labelVictoirePartie.setStyle("-fx-text-fill: #3b9a1e");
        } else {
            labelVictoirePartie.setText("Partie perdue!");
            labelVictoirePartie.setStyle("-fx-text-fill: #db3a34");
        }
    }

    public void resumerManches() {
        // Resume manche 1
        Manche rManche1 = partieService.getPartie().getManches().get(0);
        if (rManche1.getNbEssais() > 0) {
            // Affichage
            paneManche1.setVisible(true);
            // Etat de la manche
            if (rManche1.isVictoire()) {
                labelVictoireManche1.setText("Gagnée");
                labelVictoireManche1.setStyle("-fx-text-fill: #3b9a1e");
            } else {
                labelVictoireManche1.setText("Perdue");
                labelVictoireManche1.setStyle("-fx-text-fill: #db3a34");
            }
            // Mot à trouver
            labelMotATrouverManche1.setText(rManche1.getMotATrouver().retournerMotEnString());
            // Nombre d'essai
            labelNombreEssaiManche1.setText(String.valueOf(rManche1.getNbEssais()));
            // Temps passé
            // TODO calcul temps de la manche
            // Ajout pour le tri
            manchesJouees.add(rManche1);
        } else {
            paneManche1.setVisible(false);
        }

        // Resume manche 2
        Manche rManche2 = partieService.getPartie().getManches().get(1);
        if (rManche2.getNbEssais() > 0) {
            // Affichage
            paneManche2.setVisible(true);
            // Etat de la manche
            if (rManche2.isVictoire()) {
                labelVictoireManche2.setText("Gagnée");
                labelVictoireManche2.setStyle("-fx-text-fill: #3b9a1e");
            } else {
                labelVictoireManche2.setText("Perdue");
                labelVictoireManche2.setStyle("-fx-text-fill: #db3a34");
            }
            // Mot à trouver
            labelMotATrouverManche2.setText(rManche2.getMotATrouver().retournerMotEnString().toUpperCase());
            // Nombre d'essai
            labelNombreEssaiManche2.setText(String.valueOf(rManche2.getNbEssais()));
            // Temps passé
            // TODO calcul temps de la manche
            // Ajout pour le tri
            manchesJouees.add(rManche2);
        } else {
            paneManche2.setVisible(false);
        }

        // Resume manche 3
        Manche rManche3 = partieService.getPartie().getManches().get(2);
        if (rManche3.getNbEssais() > 0) {
            // Affichage
            paneManche3.setVisible(true);
            // Etat de la manche
            if (rManche3.isVictoire()) {
                labelVictoireManche3.setText("Gagnée");
                labelVictoireManche3.setStyle("-fx-text-fill: #3b9a1e");
            } else {
                labelVictoireManche3.setText("Perdue");
                labelVictoireManche3.setStyle("-fx-text-fill: #db3a34");
            }
            // Mot à trouver
            labelMotATrouverManche3.setText(rManche3.getMotATrouver().retournerMotEnString().toUpperCase());
            // Nombre d'essai
            labelNombreEssaiManche3.setText(String.valueOf(rManche3.getNbEssais()));
            // Temps passé
            // TODO calcul temps de la manche
            // Ajout pour le tri
            manchesJouees.add(rManche3);
        } else {
            paneManche3.setVisible(false);
        }

        // Resume manche 4
        Manche rManche4 = partieService.getPartie().getManches().get(3);
        if (rManche4.getNbEssais() > 0) {
            // Affichage
            paneManche4.setVisible(true);
            // Etat de la manche
            if (rManche4.isVictoire()) {
                labelVictoireManche4.setText("Gagnée");
                labelVictoireManche4.setStyle("-fx-text-fill: #3b9a1e");
            } else {
                labelVictoireManche4.setText("Perdue");
                labelVictoireManche4.setStyle("-fx-text-fill: #db3a34");
            }
            // Mot à trouver
            labelMotATrouverManche4.setText(rManche4.getMotATrouver().retournerMotEnString().toUpperCase());
            // Nombre d'essai
            labelNombreEssaiManche4.setText(String.valueOf(rManche4.getNbEssais()));
            // Temps passé
            // TODO calcul temps de la manche
            // Ajout pour le tri
            manchesJouees.add(rManche4);
        } else {
            paneManche4.setVisible(false);
        }
    }
}

package fr.esgi.controller;

import fr.esgi.App;
import fr.esgi.business.Manche;
import fr.esgi.service.MancheService;
import fr.esgi.service.PartieService;
import fr.esgi.service.impl.MancheServiceImpl;
import fr.esgi.service.impl.PartieServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ResumePartieController implements Initializable {
    private static PartieService partieService = new PartieServiceImpl();
    private static MancheService mancheService = new MancheServiceImpl();

    // Boutons
    @FXML
    private Button menu, boutonTriSurLeMot, boutonTriSurLeTemps;

    /**
     * Affichage état de la partie
     */
    @FXML
    private Label labelVictoirePartie;

    /**
     * Bloc de manche
     */
    @FXML
    private Pane paneManche1, paneManche2, paneManche3, paneManche4;
    /**
     * Affichage état de la manche
     */
    @FXML
    Label labelVictoireManche1, labelVictoireManche2, labelVictoireManche3, labelVictoireManche4;

    /**
     * Affichage mot à trouver manche
     */
    @FXML
    Label labelMotATrouverManche1, labelMotATrouverManche2, labelMotATrouverManche3, labelMotATrouverManche4;
    /**
     * Affichage nombre d'éssai manche
     */
    @FXML
    Label labelNombreEssaiManche1, labelNombreEssaiManche2, labelNombreEssaiManche3, labelNombreEssaiManche4;
    /**
     * Affichage temps passé manche
     */
    @FXML
    Label tempsPasseManche1, tempsPasseManche2, tempsPasseManche3, tempsPasseManche4;

    // Liste de manche jouées
    private static ArrayList<Manche> manchesJouees = new ArrayList<Manche>();

    // Position des panes
    ArrayList<Integer> positionX = new ArrayList<Integer>(List.of(54, 410, 54, 410));
    ArrayList<Integer> positionY = new ArrayList<Integer>(List.of(152, 152, 419, 419));


    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Style
        styliserEcran();
        // Nettoyage de mancheJouees pour une eventuelle seconde partie
        manchesJouees.clear();
        // Etat de la partie
        statuerEtatPartie();
        // Remplir les résumés de manche
        resumerManches();
    }


    /**
     * Cette méthode gère l'hovering des boutons servant à montrer les boutons survolés par la souris de l'utilisateur.
     */
    public void styliserEcran() {
        // Hover sur boutons
        menu.setOnMouseEntered(event -> menu.setOpacity(0.5));
        menu.setOnMouseExited(event -> menu.setOpacity(1));
        boutonTriSurLeMot.setOnMouseEntered(event -> boutonTriSurLeMot.setOpacity(0.5));
        boutonTriSurLeMot.setOnMouseExited(event -> boutonTriSurLeMot.setOpacity(1));
        boutonTriSurLeTemps.setOnMouseEntered(event -> boutonTriSurLeTemps.setOpacity(0.5));
        boutonTriSurLeTemps.setOnMouseExited(event -> boutonTriSurLeTemps.setOpacity(1));
    }

    /**
     * Permet d'afficher le statut de la partie.
     */
    public void statuerEtatPartie() {
        if (partieService.getPartie().isVictoire()) {
            labelVictoirePartie.setText("Partie gagnée!");
            labelVictoirePartie.setStyle("-fx-text-fill: #3b9a1e");
        } else {
            labelVictoirePartie.setText("Partie perdue!");
            labelVictoirePartie.setStyle("-fx-text-fill: #db3a34");
        }
    }

    /**
     * Méthode qui a pour but de résumer les détails de chaque manche jouée et de les afficher dans l'interface utilisateur. Pour chaque manche (de la première à la quatrième), les détails suivants sont résumés : victoire ou défaite, mot à trouver, nombre d'essais, temps passé. Chaque manche est récupérée depuis la partie en cours. Si le nombre d'essais effectués dans la manche est supérieur à zéro, cela signifie que la manche a été jouée. Dans ce cas, les détails de la manche sont affichés et ajoutés à la liste manchesJouees. Sinon, le panneau de la manche est caché.
     */
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
            labelMotATrouverManche1.setText(rManche1.getMotATrouver().retournerMotEnString().toUpperCase());
            // Nombre d'essai
            labelNombreEssaiManche1.setText(String.valueOf(rManche1.getNbEssais()));
            // Temps passé
            tempsPasseManche1.setText(TempsPasseFormate(rManche1));
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
            tempsPasseManche2.setText(TempsPasseFormate(rManche2));
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
            tempsPasseManche3.setText(TempsPasseFormate(rManche3));
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
            tempsPasseManche4.setText(TempsPasseFormate(rManche4));
            // Ajout pour le tri
            manchesJouees.add(rManche4);
        } else {
            paneManche4.setVisible(false);
        }
    }

    /**
     * Cette méthode a pour objectif de formater le temps passé dans une manche donnée en une chaîne de caractères lisible par l'utilisateur. Elle prend en paramètree une Manche contenant l'heure de début et l'heure de fin. Elle récupère le temps passé et le converti en string.
     * @param manche Manche pour laquelle on cherche la durée.
     * @return La durée de la manche convertie en String
     */
    public static String TempsPasseFormate(Manche manche) {
        long tempsPasse;
        long minutes;
        long secondes;
        String tempsPasseFormate;

        tempsPasse = mancheService.calculerTempsPasse(manche);

        if (tempsPasse > 59) {
            minutes = tempsPasse/60;
            secondes = tempsPasse%60;

            tempsPasseFormate = minutes + " min " + secondes + " sec";
        } else {
            tempsPasseFormate = tempsPasse + " sec";
        }
        return tempsPasseFormate;
    }

    /**
     * Bouton pour trier les resultats par mot.
     * @param actionEvent
     */
    @FXML
    public void trierParMot(ActionEvent actionEvent) {
        boutonTriSurLeTemps.setOpacity(1);
        boutonTriSurLeTemps.setOnMouseExited(event -> boutonTriSurLeTemps.setOpacity(1));
        boutonTriSurLeMot.setOpacity(0.5);
        boutonTriSurLeMot.setOnMouseExited(event -> boutonTriSurLeMot.setOpacity(0.5));
        partieService.trierManchesParMot(manchesJouees);

        // Afficher trié
        afficherPaneTriees();
    }
    /**
     * Bouton pour trier les resultats par temps.
     * @param actionEvent
     */
    @FXML
    public void trierParTemps(ActionEvent actionEvent) {
        boutonTriSurLeTemps.setOpacity(0.5);
        boutonTriSurLeTemps.setOnMouseExited(event -> boutonTriSurLeTemps.setOpacity(0.5));
        boutonTriSurLeMot.setOpacity(1);
        boutonTriSurLeMot.setOnMouseExited(event -> boutonTriSurLeMot.setOpacity(1));
        partieService.trierManchesParTempsPasse(manchesJouees);

        // Afficher trié
        afficherPaneTriees();
    }
    /**
     * Permet d'afficher les resultats par le tri selectionné.
     */
    public void afficherPaneTriees() {
        for (int i = 0; i < manchesJouees.size(); i++) {
            switch (manchesJouees.get(i).getNumManche()) {
                case 1:
                    paneManche1.setLayoutX(positionX.get(i));
                    paneManche1.setLayoutY(positionY.get(i));
                    break;
                case 2:
                    paneManche2.setLayoutX(positionX.get(i));
                    paneManche2.setLayoutY(positionY.get(i));
                    break;
                case 3:
                    paneManche3.setLayoutX(positionX.get(i));
                    paneManche3.setLayoutY(positionY.get(i));
                    break;
                case 4:
                    paneManche4.setLayoutX(positionX.get(i));
                    paneManche4.setLayoutY(positionY.get(i));
                    break;
                default:
                    break;
            }
        }
    }
}
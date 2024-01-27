package fr.esgi;

import fr.esgi.business.Lettre;
import fr.esgi.business.Mot;
import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import fr.esgi.service.ImportMotsService;
import fr.esgi.service.impl.ImportMotsServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Random;


/**
 * JavaFX App
 */
public class App extends Application {
    private static Random random = new Random();

    private static ImportMotsService importMotsService = new ImportMotsServiceImpl();

    private static Scene scene;
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        //launch();
        System.out.println("Bienvenue sur motus !");
        boolean resultatPartie;
        boolean rejouer = true;
        // Boucle de jeu (on relance une partie tant que le joueur le veut)
        while (rejouer) {
            // Lancement d'une partie
            lancerNouvellePartie();
            System.out.println("Voulez-vous rejouer ? (y/N)");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
                rejouer = false;
                System.out.println("À bientôt !");
            }
        }
    }

    public static void lancerNouvellePartie() {
        System.out.println("Lancement d'une nouvelle partie...");
        Partie partie = new Partie();
        int compteurManches = 1;
        // Boucle d'une partie (4 manches)
        while (compteurManches < 5) {
            // Lancement d'une manche
            System.out.println("Manche "+compteurManches);
            Manche mancheActuelle = lancerNouvelleManche(compteurManches);
            if (!mancheActuelle.isVictoire()) {
                // Arrêt de la partie si une manche est perdue
                partie.setVictoire(false);
                break;
            }
            ++compteurManches;
        }
        // Affichage du résumé de la partie
        if (partie.isVictoire()) {
            System.out.println("Partie perdue :(");
        } else {
            System.out.println("Partie gagnée :)");
        }
        System.out.println("Résumé de la partie :");
        // Résumé manche par manche
        for (Manche manche : partie.getManches()) {
            System.out.println("Manche "+manche.getNumManche()+" :");
            //System.out.println("- Mot à trouver : "+manche.getMotATrouver().retournerMotEnString());
            System.out.println("- Nombre d'essais : "+manche.getNbEssais());
            if (manche.isVictoire()) {
                System.out.println("Perdue");
            } else {
                System.out.println("- Temps écoulé : "+manche.calculerTempsPasse()/60+"m"+manche.calculerTempsPasse()%60+"s");
                System.out.println("Gagnée");
            }
        }
    }

    private static Manche lancerNouvelleManche(int numManche) {
        Manche manche = new Manche(numManche);
        return manche;
    }
    
     private static Mot initMotATrouver() {
        // Creation de la liste de mots à partir des trois dictionnaires
        importMotsService.creerListeMots();

        // Selection aleatoire du mot
        int randomIndex = random.nextInt(importMotsService.recupererListeMots().size());
        String stringMotATrouver = importMotsService.recupererListeMots().get(randomIndex);

        // Mise en objet Mot la chaine de caractere stringMotATrouver
        return formerMotATrouver(stringMotATrouver);
    }

    private static Mot formerMotATrouver(String stringMot) {
        Mot stringEnMot = new Mot();

        for (int i = 0; i < stringMot.length(); i++) {
            Lettre lettre = new Lettre();
            lettre.setCaractere(stringMot.charAt(i));
            lettre.setStatutValide();
            lettre.setPosition(i);
            //lettre.setOccurence(calculerOccurence(stringMotATrouver, stringMotATrouver.charAt(i)));
            stringEnMot.ajouterLettre(lettre);
        }
        return stringEnMot;
    }
}
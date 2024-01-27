package fr.esgi;

import fr.esgi.business.Lettre;
import fr.esgi.business.Mot;
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
        launch();
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
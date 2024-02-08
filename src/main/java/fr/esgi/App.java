package fr.esgi;

import fr.esgi.business.Lettre;
import fr.esgi.service.MotService;
import fr.esgi.service.PartieService;
import fr.esgi.service.impl.MotServiceImpl;
import fr.esgi.service.DictionnaireService;
import fr.esgi.service.impl.DictionnaireServiceImpl;
import fr.esgi.service.impl.PartieServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

/**
 * JavaFX App
 */
public class App extends Application {


    private static DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
    private static MotService motService = new MotServiceImpl();
    private static PartieService partieService = new PartieServiceImpl();

    private static Scene scene;
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"));
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

        /*System.out.println("Bienvenue sur motus !");
        boolean rejouer = true;
        // Boucle de jeu (on relance une partie tant que le joueur le veut)
        while (rejouer) {
            // Lancement d'une partie
            partieService.lancerNouvellePartie();
            System.out.println("Voulez-vous rejouer ? (y/N)");
            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
                rejouer = false;
                System.out.println("À bientôt !");
                System.exit(0);
            }
        }*/

    }

    public static ArrayList<Lettre> concatenerDeuxListesLettres(ArrayList<Lettre> listeFinale, ArrayList<Lettre> listeAAjouter){
        for(Lettre lettre:listeAAjouter){
            listeFinale.add(lettre);
        }
        return listeFinale;
    }

}
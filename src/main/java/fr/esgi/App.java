package fr.esgi;

import fr.esgi.business.Lettre;
import fr.esgi.business.Manche;
import fr.esgi.service.MancheService;
import fr.esgi.service.MotService;
import fr.esgi.service.PartieService;
import fr.esgi.service.impl.MancheServiceImpl;
import fr.esgi.service.impl.MotServiceImpl;
import fr.esgi.service.DictionnaireService;
import fr.esgi.service.impl.DictionnaireServiceImpl;
import fr.esgi.service.impl.PartieServiceImpl;

import fr.esgi.utils.ComparateurMancheParMot;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.*;

/**
 * JavaFX App
 */
public class App extends Application {


    private static DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
    private static MotService motService = new MotServiceImpl();
    private static PartieService partieService = new PartieServiceImpl();

    // DEV
    private static MancheService mancheService = new MancheServiceImpl();

    private static Scene scene;
    private static Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"));
        stage.setScene(scene);
        stage.show();
    }

    /*@Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        ModuleLayer.Controller grillesController = loader.getController();

        Scene scene = new Scene(root);

        // Gestion du clavier
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                System.out.println(keyEvent.getCode());
            }
        });

        stage.setScene(scene);
        stage.show();
    }*/

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        //launch();
        ArrayList<Manche> manches = new ArrayList<Manche>();
        Manche m1 = mancheService.lancerNouvelleManche(1);
        m1.setNbEssais(5);
        manches.add(m1);
        Manche m2 = mancheService.lancerNouvelleManche(2);
        m2.setNbEssais(4);
        manches.add(m2);
        Manche m3 = mancheService.lancerNouvelleManche(3);
        m3.setNbEssais(3);
        manches.add(m3);
        Manche m4 = mancheService.lancerNouvelleManche(4);
        m4.setNbEssais(1);
        manches.add(m4);

        partieService.trierManchesParEssai(manches);
        //partieService.trierManchesParMot(manches);

        System.out.println(manches);

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
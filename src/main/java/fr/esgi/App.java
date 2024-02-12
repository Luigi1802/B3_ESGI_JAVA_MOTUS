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

    public static ArrayList<Lettre> concatenerDeuxListesLettres(ArrayList<Lettre> listeFinale, ArrayList<Lettre> listeAAjouter){
        for(Lettre lettre:listeAAjouter){
            listeFinale.add(lettre);
        }
        return listeFinale;
    }

}
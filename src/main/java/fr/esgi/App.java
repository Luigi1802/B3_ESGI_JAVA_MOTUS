package fr.esgi;

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
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JavaFX App
 */
public class App extends Application {

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

    private static Stream<String> retournerImportMot(String urlPath)  {
        URL url = null;
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        try {
            url = new URL(urlPath);
        } catch (MalformedURLException e) {
            System.out.println("Fichier teste indisponible, raison : " + e.getMessage());
            System.exit(-1);
        }
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((url.openStream())));
            Stream<String> streamLines = bufferedReader.lines();

            return  streamLines.filter(pattern.asPredicate().negate());

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private static ArrayList<String> creerListeMots(){
        ArrayList<String> listeDesMots = new ArrayList<>();
        listeDesMots.addAll(new ArrayList<String>(retournerImportMot("https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire6.txt").collect(Collectors.toList())));
        listeDesMots.addAll(new ArrayList<String>(retournerImportMot("https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire7.txt").collect(Collectors.toList())));
        listeDesMots.addAll(new ArrayList<String>(retournerImportMot("https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire8.txt").collect(Collectors.toList())));
        return listeDesMots;
    }

    public static void main(String[] args) {
        launch();
    }

}
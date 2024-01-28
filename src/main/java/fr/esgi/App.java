package fr.esgi;

import fr.esgi.business.Manche;
import fr.esgi.business.Mot;
import fr.esgi.business.Partie;
import fr.esgi.service.MotService;
import fr.esgi.service.impl.MotServiceImpl;
import fr.esgi.service.ImportMotsService;
import fr.esgi.service.impl.ImportMotsServiceImpl;
import fr.esgi.utils.ComparateurLettreParPosition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Random random = new Random();


    private static ImportMotsService importMotsService = new ImportMotsServiceImpl();
    private static MotService motService = new MotServiceImpl();


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
        ArrayList<Lettre> lettres1 = new ArrayList<>();
        Mot mot1 = new Mot();
        String string1 = "bouliste";
        List<Character> characterList1 = string1.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for(int i =0 ; i<characterList1.size(); i++){
            lettres1.add(new Lettre(characterList1.get(i), i, "DEFAUT", 1));
        }
        mot1.setLettres(lettres1);

        ArrayList<Lettre> lettres2 = new ArrayList<>();
        Mot mot2 = new Mot();
        String string2 = "bouilles";
        List<Character> characterList2 = string2.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        for(int i =0 ; i<characterList2.size(); i++){
            lettres2.add(new Lettre(characterList2.get(i), i, "DEFAUT", 1));
        }
        mot2.setLettres(lettres2);

        Mot motIntermediaire = new Mot();
        ArrayList<Lettre> test = new ArrayList<>();
        test.add(new Lettre('b', 0, "VALIDE", 1));
        test.add(new Lettre('o', 1, "TROUVE", 1));

        motIntermediaire.setLettres(test);


        comparateurMotsSaisiATrouver(mot1 ,mot2, motIntermediaire);
//        System.out.println(mot1);
//        System.out.println(mot2);

//        System.out.println("Bienvenue sur motus !");
//        boolean resultatPartie;
//        boolean rejouer = true;
//        // Boucle de jeu (on relance une partie tant que le joueur le veut)
//        while (rejouer) {
//            // Lancement d'une partie
//            lancerNouvellePartie();
//            System.out.println("Voulez-vous rejouer ? (y/N)");
//            if (!scanner.nextLine().equalsIgnoreCase("Y")) {
//                rejouer = false;
//                System.out.println("À bientôt !");
//            }
//        }
        exit(0);

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

    private static void comparateurMotsSaisiATrouver(Mot motSaisi, Mot motAtrouver, Mot motIntermediaire){
        ArrayList<Lettre> lettresMotSaisi = new ArrayList<>(motSaisi.getLettres());
        lettresMotSaisi = (ArrayList<Lettre>) lettresMotSaisi.stream().sorted(new ComparateurLettreParPosition()).collect(Collectors.toList());

        ArrayList<Lettre> lettresMotATrouver = new ArrayList<>(motAtrouver.getLettres());
        lettresMotATrouver = (ArrayList<Lettre>) lettresMotATrouver.stream().sorted(new ComparateurLettreParPosition()).collect(Collectors.toList());

        ArrayList<Lettre> lettresValidees = new ArrayList<>();

        for(int i = 0; i<lettresMotATrouver.size(); i++){
            if (lettresMotATrouver.get(i).getCaractere().equals(lettresMotSaisi.get(i).getCaractere())){
                lettresValidees.add(lettresMotSaisi.get(i));
                lettresMotSaisi.remove(i);
                lettresMotATrouver.remove(i);
                i--;
            }
        }

        ArrayList<Lettre> lettresTrouvees = new ArrayList<>();
        for(int i = 0; i<lettresMotATrouver.size(); i++){
            List<Character> charListMotATrouver = lettresMotATrouver.stream().map(Lettre::getCaractere).collect(Collectors.toList());
            Character charSaisi = lettresMotSaisi.get(i).getCaractere();
            if(charListMotATrouver.contains(charSaisi)){
                lettresTrouvees.add(lettresMotSaisi.get(i));
                lettresMotSaisi.remove(i);
                int indexOfCharATrouver = lettresMotATrouver.stream().map(Lettre::getCaractere).collect(Collectors.toList()).indexOf(charSaisi);
                lettresMotATrouver.remove(indexOfCharATrouver);
                charListMotATrouver.remove(indexOfCharATrouver);
                i--;
            }
        }
        ArrayList<Lettre> lettresAbsentes = new ArrayList<>(lettresMotSaisi);
        System.out.println(motSaisi.getLettres());

        /* Les lettres dans les listes lettresValidees, lettresTrouvees, lettresAbsentes sont des références aux lettres contenues dans motSaisi.lettres
          La liste motSaisi.lettres n'est jamais modifiée par la fonction (il n'y a pas de changement de l'ordre ni de suppression des éléments) */
        lettresValidees.forEach(Lettre::setStatutValide);
        lettresTrouvees.forEach(Lettre::setStatutTrouve);
        lettresAbsentes.forEach(Lettre::setStatutAbsent);

        /* lettresIntermediaires est utilisée comme une référence à motIntermediaire.lettres */
        ArrayList<Lettre> lettresIntermediaires = motIntermediaire.getLettres();
        System.out.println("Mot intermediaire Lettres => " + motIntermediaire.getLettres());

        lettresValidees.forEach(lValidee -> {
            boolean lettreExiste = false;
            for(Lettre lettreIntermediaire:lettresIntermediaires){
                if (lValidee.equals(lettreIntermediaire)){
                    lettreExiste = true;
                    break;
                }else if(lValidee.estTrouve(lettreIntermediaire)){
                    lettresIntermediaires.remove(lettreIntermediaire);
                    break;
                }
            }
            if(!lettreExiste){
                lettresIntermediaires.add(lValidee);
            }
                });

        lettresTrouvees.forEach(lTrouvees -> {
            boolean lettreExiste = false;
            for(Lettre lettreIntermediaire:lettresIntermediaires){
                if(lTrouvees.estTrouve(lettreIntermediaire) || lTrouvees.estValide(lettreIntermediaire)){
                    lettreExiste = true;
                    break;
                }
            }
            if(!lettreExiste){
                lettresIntermediaires.add(lTrouvees);
            }
        });

        lettresAbsentes.forEach(lAbsente ->{
                boolean lettreExiste = false;
                for(Lettre lettreIntermediaire:lettresIntermediaires){
                    if(lAbsente.estTrouve(lettreIntermediaire) || lAbsente.estValide(lettreIntermediaire) || lAbsente.estAbsent(lettreIntermediaire)){
                        lettreExiste = true;
                        break;
                    }
                }
            if(!lettreExiste){
                lettresIntermediaires.add(lAbsente);
            }
            }
        );



        System.out.println("motSaisi.getLettres()" + motSaisi.getLettres());
        System.out.println("Mot intermediaire Lettres => " + motIntermediaire.getLettres());


    }

    public static ArrayList<Lettre> concatenerDeuxListesLettres(ArrayList<Lettre> listeFinale, ArrayList<Lettre> listeAAjouter){
        for(Lettre lettre:listeAAjouter){
            listeFinale.add(lettre);
        }
        return listeFinale;
    }

    private static Manche lancerNouvelleManche(int numManche) {
        Manche manche = new Manche(numManche);
        return manche;
    }


}
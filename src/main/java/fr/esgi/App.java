package fr.esgi;

import fr.esgi.business.Lettre;
import fr.esgi.business.Manche;
import fr.esgi.business.Mot;
import fr.esgi.business.Partie;
import fr.esgi.service.MotService;
import fr.esgi.service.impl.MotServiceImpl;
import fr.esgi.service.DictionnaireService;
import fr.esgi.service.impl.DictionnaireServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Random random = new Random();


    private static DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
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
        partie.setVictoire(true);
        int compteurManches = 1;
        // Boucle d'une partie (4 manches)
        while (compteurManches < 5) {
            // Lancement d'une manche
            System.out.println("Manche "+compteurManches);
            Manche mancheActuelle = lancerNouvelleManche(compteurManches);
            partie.ajouterManche(mancheActuelle);
            if (!mancheActuelle.isVictoire()) {
                // Arrêt de la partie si une manche est perdue
                partie.setVictoire(false);
                break;
            }
            ++compteurManches;
        }
        // Affichage du résumé de la partie
        if (partie.isVictoire()) {
            System.out.println("Partie gagnée :)");
        } else {
            System.out.println("Partie perdue :(");
        }
        System.out.println("Résumé de la partie :");
        // Résumé manche par manche
        for (Manche manche : partie.getManches()) {
            System.out.println("Manche "+manche.getNumManche()+" :");
            System.out.println("- Mot à trouver : "+manche.getMotATrouver().retournerMotEnString());
            System.out.println("- Nombre d'essais : "+manche.getNbEssais());
            if (!manche.isVictoire()) {
                System.out.println("Perdue");
            } else {
                System.out.println("- Temps écoulé : "+manche.calculerTempsPasse()/60+"m"+manche.calculerTempsPasse()%60+"s");
                System.out.println("Gagnée");
            }
        }
    }

    private static Manche lancerNouvelleManche(int numManche) {
        Manche manche = new Manche(numManche);
        // Initialisation du mot à trouver
        motService.setMotATrouver(initMotATrouver());
        manche.setMotATrouver(motService.getMotATrouver());

        // Clear de motSaisi et motIntermediaire
        motService.setMotSaisi(new Mot());
        motService.setMotIntermediaire(new Mot());
        // Ajout de la première lettre de motATrouver dans motIntermédiaire
        Lettre premiereLettre = motService.getMotATrouver().getLettres().get(0);
        premiereLettre.setStatutValide();
        motService.getMotIntermediaire().ajouterLettre(premiereLettre);
        // Boucle des saisies joueur
        for (int i = 0; i < 6; i++){
            System.out.println(motService.retournerMotIntermediaireFormate());
            motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
            // Test si mot de la bonne taille et dans le dictionnaire
            while (motService.getMotSaisi().getLettres().size() != motService.getMotATrouver().getLettres().size()) {
                System.out.println("Le mot doit compter "+motService.getMotATrouver().getLettres().size()+" lettres.");
                motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
            }
            while (!dictionnaireService.testerMotPresentDictionnaire(motService.getMotSaisi().retournerMotEnString())) {
                System.out.println("Le mot saisi n'existe pas dans le dictionnaire.");
                motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
            }
            manche.ajouterEssai();
            // Comparaison motSaisi - motATrouver
            motService.comparateurMotsSaisiATrouver();
            // Affichage motSaisi traité
            System.out.println(motService.retournerMotSaisiFormate());
            // Test victoire
            if (motService.testerValiditeMotSaisi()) {
                System.out.println("Manche gagnée !");
                manche.setHeureFin(LocalDateTime.now());
                manche.setVictoire(true);
                return manche;
            }
        }
        System.out.println("Manche perdue...");
        manche.setHeureFin(LocalDateTime.now());
        manche.setVictoire(false);
        return manche;
    }

    private static Mot initMotATrouver() {
        // Creation de la liste de mots à partir des trois dictionnaires
        dictionnaireService.creerListeMots();

        // Selection aleatoire du mot
        int randomIndex = random.nextInt(dictionnaireService.recupererListeMots().size());
        String stringMotATrouver = dictionnaireService.recupererListeMots().get(randomIndex);

        // Mise en objet Mot la chaine de caractere stringMotATrouver
        return motService.retournerStringEnMot(stringMotATrouver);
    }

    public static ArrayList<Lettre> concatenerDeuxListesLettres(ArrayList<Lettre> listeFinale, ArrayList<Lettre> listeAAjouter){
        for(Lettre lettre:listeAAjouter){
            listeFinale.add(lettre);
        }
        return listeFinale;
    }

}
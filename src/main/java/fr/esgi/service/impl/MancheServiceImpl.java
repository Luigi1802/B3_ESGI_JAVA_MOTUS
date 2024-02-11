package fr.esgi.service.impl;

import fr.esgi.App;
import fr.esgi.business.Lettre;
import fr.esgi.business.Manche;
import fr.esgi.business.Mot;
import fr.esgi.service.DictionnaireService;
import fr.esgi.service.MancheService;
import fr.esgi.service.MotService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class MancheServiceImpl implements MancheService {
    private static DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
    private static MotService motService = new MotServiceImpl();


    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);

    private static Manche manche = new Manche();

    @Override
    public void setManche(Manche manche) {MancheServiceImpl.manche = manche;}

    @Override
    public Manche getManche() {return manche;}

    @Override
    public Manche lancerNouvelleManche(int numManche) throws IOException {
        manche = new Manche(numManche);
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
        App.setRoot("grilles");
        //while (!manche.isVictoire() || manche.getNbEssais() < 6) {
            // déroulement de la manche
        //}
        /*for (int i = 0; i < 6; i++){
            System.out.println(motService.retournerMotIntermediaireFormate());
            //motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
            // Test si mot de la bonne taille et dans le dictionnaire
            while (motService.getMotSaisi().getLettres().size() != motService.getMotATrouver().getLettres().size()
                    || !dictionnaireService.testerMotPresentDictionnaire(motService.getMotSaisi().retournerMotEnString())) {
                if (motService.getMotSaisi().getLettres().size() != motService.getMotATrouver().getLettres().size()) {
                    System.out.println("Le mot doit compter "+motService.getMotATrouver().getLettres().size()+" lettres.");
                } else {
                    System.out.println("Le mot saisi n'existe pas dans le dictionnaire.");
                }
                //motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
            }
            while (motService.getMotSaisi().getLettres().size() != motService.getMotATrouver().getLettres().size()) {
                System.out.println("Le mot doit compter "+motService.getMotATrouver().getLettres().size()+" lettres.");
                //motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
            }
            while (!dictionnaireService.testerMotPresentDictionnaire(motService.getMotSaisi().retournerMotEnString())) {
                System.out.println("Le mot saisi n'existe pas dans le dictionnaire.");
                //motService.setMotSaisi(motService.retournerStringEnMot(scanner.nextLine()));
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
        manche.setVictoire(false);*/
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
}

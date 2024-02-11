package fr.esgi.service.impl;

import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import fr.esgi.controller.GrillesController;
import fr.esgi.service.MancheService;
import fr.esgi.service.PartieService;
import fr.esgi.utils.ComparateurMancheParMot;
import fr.esgi.utils.ComparateurMancheParEssai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartieServiceImpl implements PartieService {
    private static MancheService mancheService = new MancheServiceImpl();
    private static Partie partie = new Partie();
    private ComparateurMancheParMot comparateurParMot = new ComparateurMancheParMot();
    private ComparateurMancheParEssai comparateurParEssai = new ComparateurMancheParEssai();

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        PartieServiceImpl.partie = partie;
    }
    private static GrillesController controller = new GrillesController();

    @Override
    public void lancerNouvellePartie() {
        partie = new Partie();
        System.out.println("Lancement d'une nouvelle partie...");
        partie.setVictoire(true);
        int compteurManches = 1;
        // Boucle d'une partie (4 manches)
        while (compteurManches < 5) {
            // Lancement d'une manche
            System.out.println("Manche "+compteurManches);
            Manche mancheActuelle = mancheService.lancerNouvelleManche(compteurManches);
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

    // TODO paramètre à changer en Partie et plus ArrayList une fois boucle de jeu opé
    @Override
    public ArrayList<Manche> trierManchesParMot(ArrayList<Manche> manchesPartie) {
        Collections.sort(manchesPartie, comparateurParMot);
        return manchesPartie;
    }
    @Override
    public ArrayList<Manche> trierManchesParEssai(ArrayList<Manche> manchesPartie) {
        Collections.sort(manchesPartie, comparateurParEssai);
        return manchesPartie;
    }
}

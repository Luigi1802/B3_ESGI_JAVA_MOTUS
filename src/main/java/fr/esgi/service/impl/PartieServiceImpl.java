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

import java.io.IOException;

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
    public void lancerNouvellePartie() throws IOException {
        partie = new Partie();
        partie.setVictoire(true);
        int compteurManches = 1;
        // Boucle d'une partie (4 manches)
        while (compteurManches < 5) {
            // Lancement d'une manche
            Manche mancheActuelle = mancheService.lancerNouvelleManche(compteurManches);
            partie.ajouterManche(mancheActuelle);
            if (!mancheActuelle.isVictoire()) {
                // Arrêt de la partie si une manche est perdue
                partie.setVictoire(false);
                break;
            }
            ++compteurManches;
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

package fr.esgi.service.impl;

import fr.esgi.App;
import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import fr.esgi.service.MancheService;
import fr.esgi.service.PartieService;
import fr.esgi.utils.ComparateurMancheParMot;
import fr.esgi.utils.ComparateurMancheParTempsPasse;

import java.util.ArrayList;
import java.util.Collections;

import java.io.IOException;

public class PartieServiceImpl implements PartieService {
    private static MancheService mancheService = new MancheServiceImpl();
    private static Partie partie = new Partie();
    private static int idMancheActuelle;
    private ComparateurMancheParMot comparateurMancheParMot = new ComparateurMancheParMot();
    private ComparateurMancheParTempsPasse comparateurMancheTempsPasse = new ComparateurMancheParTempsPasse();

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        PartieServiceImpl.partie = partie;
    }

    public void passerAMancheSuivante() {idMancheActuelle++;}

    public Manche getMancheActuelle() {return partie.getManches().get(idMancheActuelle);}

    public int getIdMancheActuelle() {return idMancheActuelle;}


    @Override
    public void lancerNouvellePartie() throws IOException {
        partie = new Partie();
        int compteurManches = 1;
        // Création des 4 manches
        while (compteurManches < 5) {
            // Création d'une manche
            Manche manche = mancheService.creerNouvelleManche(compteurManches);
            partie.ajouterManche(manche);
            ++compteurManches;
        }
        // Remise à zéro de l'idMancheActuelle
        idMancheActuelle = 0;
        // Lancement de l'écran de la partie
        App.setRoot("grilles");
    }

    @Override
    public void trierManchesParMot(ArrayList<Manche> manchesPartie) {
        Collections.sort(manchesPartie, comparateurMancheParMot);
    }
    @Override
    public void trierManchesParTempsPasse(ArrayList<Manche> manchesPartie) {
        Collections.sort(manchesPartie, comparateurMancheTempsPasse);
    }
}

package fr.esgi.service.impl;

import fr.esgi.App;
import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import fr.esgi.controller.GrillesController;
import fr.esgi.service.MancheService;
import fr.esgi.service.PartieService;

import java.io.IOException;

public class PartieServiceImpl implements PartieService {
    private static MancheService mancheService = new MancheServiceImpl();
    private static Partie partie = new Partie();
    private static int idMancheActuelle;
    private static GrillesController controller = new GrillesController();

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
        partie.setVictoire(true);
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
}

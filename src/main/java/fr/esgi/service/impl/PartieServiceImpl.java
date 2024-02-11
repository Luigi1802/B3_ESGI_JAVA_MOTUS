package fr.esgi.service.impl;

import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import fr.esgi.controller.GrillesController;
import fr.esgi.service.MancheService;
import fr.esgi.service.PartieService;

import java.io.IOException;

public class PartieServiceImpl implements PartieService {
    private static MancheService mancheService = new MancheServiceImpl();
    private static Partie partie = new Partie();

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
}

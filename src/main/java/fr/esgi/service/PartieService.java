package fr.esgi.service;

import fr.esgi.business.Partie;

public interface PartieService {

    Partie getPartie();
    void setPartie(Partie partie);
    void lancerNouvellePartie();
}

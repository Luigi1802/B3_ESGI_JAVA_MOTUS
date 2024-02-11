package fr.esgi.service;

import fr.esgi.business.Partie;

import java.io.IOException;

public interface PartieService {

    Partie getPartie();
    void setPartie(Partie partie);
    void lancerNouvellePartie() throws IOException;
}

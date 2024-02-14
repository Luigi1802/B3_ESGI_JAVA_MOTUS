package fr.esgi.service;

import fr.esgi.business.Manche;
import fr.esgi.business.Partie;

import java.util.ArrayList;
import java.io.IOException;

public interface PartieService {

    Partie getPartie();
    void setPartie(Partie partie);
    void passerAMancheSuivante();
    Manche getMancheActuelle();
    int getIdMancheActuelle();
    void lancerNouvellePartie() throws IOException;

    void trierManchesParMot(ArrayList<Manche> manchesPartie);

    void trierManchesParTempsPasse(ArrayList<Manche> manchesPartie);
}

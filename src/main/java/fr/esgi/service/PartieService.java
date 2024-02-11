package fr.esgi.service;

import fr.esgi.business.Manche;
import fr.esgi.business.Partie;
import javafx.scene.input.Mnemonic;

import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public interface PartieService {

    Partie getPartie();
    void setPartie(Partie partie);
    void lancerNouvellePartie() throws IOException;

    ArrayList<Manche> trierManchesParMot(ArrayList<Manche> manchesPartie);
    ArrayList<Manche> trierManchesParEssai(ArrayList<Manche> manchesPartie);

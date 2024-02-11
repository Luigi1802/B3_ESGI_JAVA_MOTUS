package fr.esgi.utils;

import fr.esgi.business.Manche;
import fr.esgi.business.Mot;

import java.util.Comparator;

public class ComparateurMancheParMot implements Comparator<Manche> {
    @Override
    public int compare(Manche manche1, Manche manche2) {
        Mot mot1 = manche1.getMotATrouver();
        Mot mot2 = manche2.getMotATrouver();

        if (mot1 == null && mot2 == null) {
            // les deux mots sont vides
            return 0;
        } else if (mot1 == null) {
            // mot vide trié après
            return 1;
        } else if (mot2 == null) {
            // mot vide trié après
            return -1;
        }

        return mot1.retournerMotEnString().compareTo(mot2.retournerMotEnString());
    }
}
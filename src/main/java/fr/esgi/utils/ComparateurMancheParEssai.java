package fr.esgi.utils;

import fr.esgi.business.Manche;
import fr.esgi.business.Mot;

import java.util.Comparator;

public class ComparateurMancheParEssai implements Comparator<Manche> {
    @Override
    public int compare(Manche manche1, Manche manche2) {
        return Integer.compare(manche1.getNbEssais(), manche2.getNbEssais());
    }
}
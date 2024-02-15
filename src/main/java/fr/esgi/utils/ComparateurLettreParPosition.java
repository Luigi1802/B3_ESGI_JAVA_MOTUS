package fr.esgi.utils;

import fr.esgi.business.Lettre;

/**
 * Cette interface permet de comparer les lettres entre elles selon l'attribut Lettre.position afin d'ordonner en ordre croissant une liste de Lettre.
 */
public class ComparateurLettreParPosition implements java.util.Comparator<Lettre> {
    @Override
    public int compare(Lettre lettre1, Lettre lettre2) {
        return Integer.compare(lettre1.getPosition() , lettre2.getPosition()) ;
    }
}

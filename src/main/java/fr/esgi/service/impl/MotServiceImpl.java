package fr.esgi.service.impl;

import fr.esgi.business.Mot;
import fr.esgi.business.Lettre;
import fr.esgi.service.MotService;

public class MotServiceImpl implements MotService{
    @Override
    public Mot retournerStringEnMot(String stringMot) {
        Mot stringEnMot = new Mot();

        for (int i = 0; i < stringMot.length(); i++) {
            Lettre lettre = new Lettre();
            lettre.setCaractere(stringMot.charAt(i));
            lettre.setPosition(i);
            stringEnMot.ajouterLettre(lettre);
        }
        return stringEnMot;
    }

}

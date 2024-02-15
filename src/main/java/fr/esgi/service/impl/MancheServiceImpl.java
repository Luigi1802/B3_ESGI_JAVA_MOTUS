package fr.esgi.service.impl;

import fr.esgi.business.Lettre;
import fr.esgi.business.Manche;
import fr.esgi.business.Mot;
import fr.esgi.service.DictionnaireService;
import fr.esgi.service.MancheService;
import fr.esgi.service.MotService;
import fr.esgi.service.PartieService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.Scanner;

public class MancheServiceImpl implements MancheService {
    private static DictionnaireService dictionnaireService = new DictionnaireServiceImpl();
    private static MotService motService = new MotServiceImpl();
    private static PartieService partieService = new PartieServiceImpl();


    private static Random random = new Random();

    private static Manche manche = new Manche();

    @Override
    public void setManche(Manche manche) {MancheServiceImpl.manche = manche;}

    @Override
    public Manche getManche() {return manche;}

    /**
     * Méthode servant à générer une nouvelle Manche. La nouvelle manche prend en paramètre un numéro de manche.
     * @param numManche Numéro de la manche dans une partie.
     * @return Manche générée
     * @throws IOException
     */
    @Override
    public Manche creerNouvelleManche(int numManche) throws IOException {
        manche = new Manche(numManche);
        manche.setMotATrouver(initMotATrouver());
        return manche;
    }

    /**
     * Méthode permettant de lancer une manche au sein d'une partie. Elle génère le mot à trouver en le récupérant dans un dictionnaire. Elle initialise à vide les variables motIntermediaire et le motSaisi qui seront utilisées par la suite.
     */
    @Override
    public void lancerManche(){
        motService.setMotATrouver(partieService.getMancheActuelle().getMotATrouver());
        // Clear de motSaisi et motIntermediaire
        motService.setMotSaisi(new Mot());
        motService.setMotIntermediaire(new Mot());
        // Ajout de la première lettre de motATrouver dans motIntermédiaire
        Lettre premiereLettre = motService.getMotATrouver().getLettres().get(0);
        premiereLettre.setStatutValide();
        motService.getMotIntermediaire().ajouterLettre(premiereLettre);
        partieService.getMancheActuelle().setHeureDebut(LocalDateTime.now());
    }

    /**
     * Méthode servant à créer la liste de mots dans laquelle sera pioché au hasard un mot à trouver. Converti la chaine de caractère ainsi piochée en Mot avant de le renvoyer.
     * @return Retourne le mot pioché converti en un Mot
     */
    private static Mot initMotATrouver() {
        // Creation de la liste de mots à partir des trois dictionnaires
        dictionnaireService.creerListeMots();

        // Selection aleatoire du mot
        int randomIndex = random.nextInt(dictionnaireService.recupererListeMots().size());
        String stringMotATrouver = dictionnaireService.recupererListeMots().get(randomIndex);

        // Mise en objet Mot la chaine de caractere stringMotATrouver
        return motService.retournerStringEnMot(stringMotATrouver);
    }

    /**
     * Méthode servant à calculer la durée d'une manche. Elle calcule la différence entree l'heure de début et l'heure de fin de la manche.
     * @param manche un Long correspondant au temps qu'a durée la manche.
     * @return
     */
    public Long calculerTempsPasse(Manche manche) {
        // Temps écoulé entre le début d'une manche et sa fin (manche gagné ou 6 essais incorrects)
        return ChronoUnit.SECONDS.between(manche.getHeureDebut(), manche.getHeureFin());
    }
}

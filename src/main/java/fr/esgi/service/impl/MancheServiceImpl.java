package fr.esgi.service.impl;

import fr.esgi.App;
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
    private static Scanner scanner = new Scanner(System.in);

    private static Manche manche = new Manche();

    @Override
    public void setManche(Manche manche) {MancheServiceImpl.manche = manche;}

    @Override
    public Manche getManche() {return manche;}

    @Override
    public Manche creerNouvelleManche(int numManche) throws IOException {
        manche = new Manche(numManche);
        manche.setMotATrouver(initMotATrouver());
        return manche;
    }

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

    private static Mot initMotATrouver() {
        // Creation de la liste de mots à partir des trois dictionnaires
        dictionnaireService.creerListeMots();

        // Selection aleatoire du mot
        int randomIndex = random.nextInt(dictionnaireService.recupererListeMots().size());
        String stringMotATrouver = dictionnaireService.recupererListeMots().get(randomIndex);

        // Mise en objet Mot la chaine de caractere stringMotATrouver
        return motService.retournerStringEnMot(stringMotATrouver);
    }

    public Long calculerTempsPasse(Manche manche) {
        return ChronoUnit.SECONDS.between(manche.getHeureDebut(), manche.getHeureFin());
    }
}

package fr.esgi.business;

import java.time.Duration;
import java.time.LocalDateTime;

public class Manche {
    private Long id;
    private int numManche;
    private Mot motATrouver;
    private int nbEssais;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private boolean victoire;
    private static Long compteur = 0L;

    // Constructeurs
    public Manche() {
        this.id = ++compteur;
        this.nbEssais = 0;
    }
    public Manche(int numManche) {
        this();
        this.numManche = numManche;
    }

    // Getter et Setter
    public Long getId() {
        return id;
    }
    public int getNumManche() {
        return numManche;
    }
    public void setNumManche(int numManche) {
        this.numManche = numManche;
    }
    public Mot getMotATrouver() {
        return motATrouver;
    }
    public void setMotATrouver(Mot motATrouver) {
        this.motATrouver = motATrouver;
    }
    public int getNbEssais() {
        return nbEssais;
    }
    public void setNbEssais(int nbEssais) {
        this.nbEssais = nbEssais;
    }
    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }
    public LocalDateTime getHeureFin() {
        return heureFin;
    }
    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }
    public void setHeureDebut(LocalDateTime heureDebut) {this.heureDebut = heureDebut;}
    public boolean isVictoire() {
        return victoire;
    }
    public void setVictoire(boolean victoire) {
        this.victoire = victoire;
    }

// toString et autre


    @Override
    public String toString() {
        return "Manche{" +
                "id=" + id +
                ", numManche=" + numManche +
                ", motATrouver=" + motATrouver +
                ", nbEssais=" + nbEssais +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", victoire=" + victoire +
                '}';
    }

    public void ajouterEssai(){
        ++nbEssais;
    }
    public Long calculerTempsPasse(){
        if (heureFin!=null) {
            return Duration.between(heureDebut, heureFin).getSeconds();
        }
        return 0L;
    }
}

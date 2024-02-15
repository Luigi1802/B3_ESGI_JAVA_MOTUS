package fr.esgi.business;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Classe métier permettant de gérer les manches d'une partie.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manche)) return false;
        Manche manche = (Manche) o;
        return getNumManche() == manche.getNumManche() && getNbEssais() == manche.getNbEssais() && isVictoire() == manche.isVictoire() && Objects.equals(getMotATrouver(), manche.getMotATrouver()) && Objects.equals(getHeureDebut(), manche.getHeureDebut()) && Objects.equals(getHeureFin(), manche.getHeureFin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumManche(), getMotATrouver(), getNbEssais(), getHeureDebut(), getHeureFin(), isVictoire());
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

    /**
     * Méthode permettant de calculer la durée d'une manche d'une partie.
     * @return Durée de la manche.
     */
    public Long calculerTempsPasse(){
        if (heureFin!=null) {
            return Duration.between(heureDebut, heureFin).getSeconds();
        }
        return 0L;
    }
}

package fr.esgi.business;

import java.util.ArrayList;

/**
 * Classe métier gérant les parties
 */
public class Partie {
    private Long id;
    private ArrayList<Manche> manches;
    private boolean victoire;
    private static Long compteur = 0L;

    // Constructeur
    public Partie(){
        this.id = ++compteur;
        this.manches = new ArrayList<Manche>();
    }

    // Getter et Setter
    public Long getId() {
        return id;
    }
    public ArrayList<Manche> getManches() {
        return manches;
    }
    public void setManches(ArrayList<Manche> manches) {
        this.manches = manches;
    }
    public boolean isVictoire() {
        return victoire;
    }
    public void setVictoire(boolean victoire) {
        this.victoire = victoire;
    }

    // toString et autre
    @Override
    public String toString() {
        return "Partie{" +
                "id=" + id +
                ", manches=" + manches +
                ", victoire=" + victoire +
                '}';
    }

    /**
     * Ajout d'une manche à la liste des manches d'une partie
     * @param manche Manche
     */
    public void ajouterManche(Manche manche) {
        this.manches.add(manche);
    }
}

package fr.esgi.business;

import java.util.ArrayList;

public class Mot {
    private Long id;
    private ArrayList<Lettre> lettres;
    private static Long compteur = 0L;

    // Constructeur
    public Mot() {
        this.id = ++compteur;
        this.lettres = new ArrayList<Lettre>();
    }

    // Getter et Setter
    public Long getId() {
        return id;
    }
    public ArrayList<Lettre> getLettres() {
        return lettres;
    }
    public void setLettres(ArrayList<Lettre> lettres) {
        this.lettres = lettres;
    }

    // toString et autre
    @Override
    public String toString() {
        return "Mot{" +
                "id=" + id +
                ", lettres=" + lettres +
                '}';
    }

    public void ajouterLettre(Lettre lettre) {
        this.lettres.add(lettre);
    }
}

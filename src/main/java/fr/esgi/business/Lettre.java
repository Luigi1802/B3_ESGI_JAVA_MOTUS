package fr.esgi.business;

import java.util.Objects;

public class Lettre {
    private Long id;
    private Character caractere;
    private int position;
    private String statut;
    // Valeurs possibles de statut :
    // - VALIDE (bonne lettre, bonne position)
    // - TROUVE (bonne lettre, mauvaise position)
    // - DEFAUT (mauvaise lettre, mauvaise position)
    private int occurence;
    private static Long compteur = 0L;

    // Constructeurs
    public Lettre(){
        this.id = ++compteur;
        this.statut = "DEFAUT";
        this.occurence = 0;
    }

    public Lettre(Character caractere, int position, String statut, int occurence) {
        this();
        this.caractere = caractere;
        this.position = position;
        this.statut = statut;
        this.occurence = occurence;
    }

    // Getter et Setter
    public Long getId() {
        return id;
    }
    public Character getCaractere() {
        return caractere;
    }
    public void setCaractere(Character caractere) {
        this.caractere = caractere;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatutValide() {
        this.statut = "VALIDE";
    }
    public void setStatutTrouve() {
        this.statut = "TROUVE";
    }
    public void setStatutDefaut() {
        this.statut = "DEFAUT";
    }
    public void setStatutAbsent() {
        this.statut = "ABSENT";
    }
    public int getOccurence() {
        return occurence;
    }
    public void setOccurence(int occurence) {
        this.occurence = occurence;
    }

    // toString et autres

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lettre)) return false;
        Lettre lettre = (Lettre) o;
        return getPosition() == lettre.getPosition() && Objects.equals(getCaractere(), lettre.getCaractere()) && Objects.equals(getStatut(), lettre.getStatut());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCaractere(), getPosition(), getStatut());
    }

    public boolean estTrouve(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lettre)) return false;
        Lettre lettre = (Lettre) o;
        return Objects.equals(getCaractere(), lettre.getCaractere()) && Objects.equals("TROUVE", lettre.getStatut());}
    public boolean estValide(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lettre)) return false;
        Lettre lettre = (Lettre) o;
        return Objects.equals(getCaractere(), lettre.getCaractere()) && Objects.equals("VALIDE", lettre.getStatut());}

    public boolean estAbsent(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lettre)) return false;
        Lettre lettre = (Lettre) o;
        return Objects.equals(getCaractere(), lettre.getCaractere()) && Objects.equals("ABSENT", lettre.getStatut());}

    @Override
    public String toString() {
        return "Lettre{" +
                "id=" + id +
                ", caractere='" + caractere + '\'' +
                ", position=" + position +
                ", statut='" + statut + '\'' +
                ", occurence=" + occurence +
                '}';
    }
}


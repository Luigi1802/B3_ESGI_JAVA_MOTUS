package fr.esgi.business;

import fr.esgi.utils.ComparateurLettreParPosition;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Classe métier concernant les mots. Chaque Mot con tient une liste de ses lettres sous la forme d'une classe métier Lettre.
 */
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

    /**
     * Prend la liste de Lettre composant le mot, récupère ses caractères et les concatène.
     * @return le Mot sous un format de chaîne de caractère.
     */
    public String retournerMotEnString(){
        return lettres.stream().sorted(new ComparateurLettreParPosition()).map(Lettre::getCaractere).map(String::valueOf).collect(Collectors.joining());
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

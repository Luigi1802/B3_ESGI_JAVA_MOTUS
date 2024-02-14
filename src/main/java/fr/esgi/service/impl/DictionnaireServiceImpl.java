package fr.esgi.service.impl;

import fr.esgi.service.DictionnaireService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * La classe dontient les méthode liées à la génération du dictionnaire de mots utilisée par le programme.
 */
public class DictionnaireServiceImpl implements DictionnaireService {
    private final String sixLettresURL = "https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire6.txt";
    private final String septLettresURL = "https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire7.txt";
    private final String huitLettresURL = "https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire8.txt";
    private static ArrayList<String> listeMots = new ArrayList<>();

    /**
     * Cette fonction utilise un URL vers un fichier texte comprenant des mots mis à la ligne,
     * chaque mot est récupéré et mis comme un élément d'une liste,
     * la liste est renvoyée sous la forme d'un Stream.
     * @param urlPath Un path URL sous la forme d'un String
     * @return Un stream de Strings qui comprend l'ensemble des mots contenu dans le fichier texte à l'adresse spécifiée en entrée.
     */
    @Override
    public  Stream<String> retournerImportMot(String urlPath)  {
        URL url = null;
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        try {
            url = new URL(urlPath);
        } catch (MalformedURLException e) {
            System.out.println("Fichier teste indisponible, raison : " + e.getMessage());
            System.out.println("Il est possible que vous ne soyez pas connecté à internet. Si le problème persiste, veuillez contacter le support.");
            System.exit(-1);
        }
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((url.openStream())));
            Stream<String> streamLines = bufferedReader.lines();

            return  streamLines.filter(pattern.asPredicate().negate());

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Récupère un stream de String et le renvoi sous le format d'une liste de String
     * @param streamATransformer Un stream de String
     * @return ArrayList<String> Ayant pour éléments les Strings du stream d'entrée
     */
    @Override
    public ArrayList<String> transformerStreamStringEnListe(Stream<String> streamATransformer){
        return streamATransformer.collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Rempli la liste des mots avec les mots aux URL fournies. Par défaut ce sont des mots de 6,7 et 8 lettres.
     */
    @Override
    public void creerListeMots(){
        listeMots.addAll(transformerStreamStringEnListe(retournerImportMot(sixLettresURL)));
        listeMots.addAll(transformerStreamStringEnListe(retournerImportMot(septLettresURL)));
        listeMots.addAll(transformerStreamStringEnListe(retournerImportMot(huitLettresURL)));
    }

    /**
     * La fonction retourne la liste des mots initialisée par le service. Si la liste n'a pas été initialisée avec creerListeMots(), elle est vide
     * @return Une ArrayList de String comprenant les mots du dictionnaire.
     */
    @Override
    public ArrayList<String> recupererListeMots(){return listeMots;};

    /**
     * Prend un mot en entrée et vérifie sa présence dans la liste du dictionnaire.
     * @param mot String
     * @return Retourne true si le mot est présent dans le dictionnaire.
     */
    @Override
    public boolean testerMotPresentDictionnaire(String mot){
        return listeMots.contains(mot.toLowerCase());
    }
}

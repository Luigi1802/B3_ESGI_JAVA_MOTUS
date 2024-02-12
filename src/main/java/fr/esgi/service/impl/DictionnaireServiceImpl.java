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

public class DictionnaireServiceImpl implements DictionnaireService {
    private final String sixLettresURL = "https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire6.txt";
    private final String septLettresURL = "https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire7.txt";
    private final String huitLettresURL = "https://raw.githubusercontent.com/gverdier/motus/master/Console/Dictionnaire8.txt";
    private static ArrayList<String> listeMots = new ArrayList<>();
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

    @Override
    public ArrayList<String> transformerStreamStringEnListe(Stream<String> streamATransformer){
        return streamATransformer.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void creerListeMots(){
        listeMots.addAll(transformerStreamStringEnListe(retournerImportMot(sixLettresURL)));
        listeMots.addAll(transformerStreamStringEnListe(retournerImportMot(septLettresURL)));
        listeMots.addAll(transformerStreamStringEnListe(retournerImportMot(huitLettresURL)));
    }
    @Override
    public ArrayList<String> recupererListeMots(){return listeMots;};
    @Override
    public boolean testerMotPresentDictionnaire(String mot){
        return listeMots.contains(mot.toLowerCase());
    }
}

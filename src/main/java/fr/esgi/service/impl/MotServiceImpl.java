package fr.esgi.service.impl;

import fr.esgi.business.Mot;
import fr.esgi.business.Lettre;
import fr.esgi.service.MotService;
import fr.esgi.utils.ComparateurLettreParPosition;
import fr.esgi.business.StatutLettre;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Classe service servant à manipuler les mots.
 */
public class MotServiceImpl implements MotService{
    private static Mot motSaisi = new Mot();
    private static Mot motATrouver = new Mot();
    private static Mot motIntermediaire = new Mot();

    @Override
    public Mot getMotATrouver() {
        return motATrouver;
    }

    @Override
    public Mot getMotIntermediaire() {
        return motIntermediaire;
    }

    @Override
    public Mot getMotSaisi() {
        return motSaisi;
    }

    @Override
    public void setMotATrouver(Mot motATrouver) {
        MotServiceImpl.motATrouver = motATrouver;
    }

    @Override
    public void setMotIntermediaire(Mot motIntermediaire) {
        MotServiceImpl.motIntermediaire = motIntermediaire;
    }

    @Override
    public void setMotSaisi(Mot motSaisi) {
        MotServiceImpl.motSaisi = motSaisi;
    }

    /**
     * Recupere un String correspondant à un mot. Créer un Mot à partir de cette chaîne de caractère. Divise la chaîne en caractère individuels, créé une Lettre associée à chaque caractère et les met dans la liste de Lettre du Mot.
     * @param stringMot Une chaîne de cara  ctère correspondant à un mot.
     * @return La chaîne de caractère mise en paramètre convertie en un nouveau Mot.
     */
    @Override
    public Mot retournerStringEnMot(String stringMot) {
        Mot stringEnMot = new Mot();

        for (int i = 0; i < stringMot.length(); i++) {
            Lettre lettre = new Lettre();
            lettre.setCaractere(stringMot.charAt(i));
            lettre.setPosition(i);
            stringEnMot.ajouterLettre(lettre);
        }
        return stringEnMot;
    }

    /**
     * Gère le motSaisi, le motATrouver et le motIntermediaire.<br>
     * motSaisi et motATrouver sont comparés selon trois critères : trouver les lettres au bon endroit TROUVE, trouver les lettres existantes mais pas au bon endroit VALIDE trouver les lettres ABSENTES.<br>
     * Les Lettre du motSaisi sont comparées au motATrouver pour changer au fur et à mesure le statut de ses lettres. A la fin du code les motSaisi est réordonné via la position de ses Lettre et ainsi utilisable pour l'affichage.<br>
     * Les Lettre du mot intermédiaire sont mises à jour en ne conservant chaque Lettre qu'une seule fois à chaque fois avec le statut le plus fort conservé pour chaque Lettre TROUVE>VALIDE>DEFAUT et ABSENT>DEFAUT.<br>
     */
    @Override
    public void comparateurMotsSaisiATrouver(){
        ArrayList<Lettre> lettresMotSaisi = new ArrayList<>(motSaisi.getLettres());
        lettresMotSaisi = (ArrayList<Lettre>) lettresMotSaisi.stream().sorted(new ComparateurLettreParPosition()).collect(Collectors.toList());

        ArrayList<Lettre> lettresMotATrouver = new ArrayList<>(motATrouver.getLettres());
        lettresMotATrouver = (ArrayList<Lettre>) lettresMotATrouver.stream().sorted(new ComparateurLettreParPosition()).collect(Collectors.toList());

        ArrayList<Lettre> lettresValidees = new ArrayList<>();

        for(int i = 0; i<lettresMotATrouver.size(); i++){
            if (lettresMotATrouver.get(i).getCaractere().equals(lettresMotSaisi.get(i).getCaractere())){
                lettresValidees.add(lettresMotSaisi.get(i));
                lettresMotSaisi.remove(i);
                lettresMotATrouver.remove(i);
                i--;
            }
        }

        ArrayList<Lettre> lettresTrouvees = new ArrayList<>();
        for(int i = 0; i<lettresMotATrouver.size(); i++){
            List<Character> charListMotATrouver = lettresMotATrouver.stream().map(Lettre::getCaractere).collect(Collectors.toList());
            Character charSaisi = lettresMotSaisi.get(i).getCaractere();
            if(charListMotATrouver.contains(charSaisi)){
                lettresTrouvees.add(lettresMotSaisi.get(i));
                lettresMotSaisi.remove(i);
                int indexOfCharATrouver = lettresMotATrouver.stream().map(Lettre::getCaractere).collect(Collectors.toList()).indexOf(charSaisi);
                lettresMotATrouver.remove(indexOfCharATrouver);
                charListMotATrouver.remove(indexOfCharATrouver);
                i--;
            }
        }
        ArrayList<Lettre> lettresAbsentes = new ArrayList<>(lettresMotSaisi);

        /* Les lettres dans les listes lettresValidees, lettresTrouvees, lettresAbsentes sont des références aux lettres contenues dans motSaisi.lettres
          La liste motSaisi.lettres n'est jamais modifiée par la fonction (il n'y a pas de changement de l'ordre ni de suppression des éléments) */
        lettresValidees.forEach(Lettre::setStatutValide);
        lettresTrouvees.forEach(Lettre::setStatutTrouve);
        lettresAbsentes.forEach(Lettre::setStatutAbsent);

        /* lettresIntermediaires est utilisée comme une référence à motIntermediaire.lettres */
        ArrayList<Lettre> lettresIntermediaires = motIntermediaire.getLettres();

        lettresValidees.forEach(lValidee -> {
            boolean lettreExiste = false;
            for(Lettre lettreIntermediaire:lettresIntermediaires){
                if (lValidee.equals(lettreIntermediaire)){
                    lettreExiste = true;
                    break;
                }else if(lValidee.estTrouve(lettreIntermediaire)){
                    lettresIntermediaires.remove(lettreIntermediaire);
                    break;
                }
            }
            if(!lettreExiste){
                lettresIntermediaires.add(lValidee);
            }
        });

        lettresTrouvees.forEach(lTrouvees -> {
            boolean lettreExiste = false;
            for(Lettre lettreIntermediaire:lettresIntermediaires){
                if(lTrouvees.estTrouve(lettreIntermediaire) || lTrouvees.estValide(lettreIntermediaire)){
                    lettreExiste = true;
                    break;
                }
            }
            if(!lettreExiste){
                lettresIntermediaires.add(lTrouvees);
            }
        });

        lettresAbsentes.forEach(lAbsente ->{
                    boolean lettreExiste = false;
                    for(Lettre lettreIntermediaire:lettresIntermediaires){
                        if(lAbsente.estTrouve(lettreIntermediaire) || lAbsente.estValide(lettreIntermediaire) || lAbsente.estAbsent(lettreIntermediaire)){
                            lettreExiste = true;
                            break;
                        }
                    }
                    if(!lettreExiste){
                        lettresIntermediaires.add(lAbsente);
                    }
                }
        );
    }

    /**
     * Test si toutes les Lettre d'un mot ont le statut VALIDE.
     * @return Renvoie true si toutes les Lettre sont valide
     */
    public boolean testerValiditeMotSaisi(){
        boolean validite = true;
        for (Lettre lettre:motSaisi.getLettres()) {
            if (!Objects.equals(lettre.getStatut(), StatutLettre.VALIDE)){
                validite = false;
                break;
            }
        }
        return validite;
    }
    @Override
    public String retournerMotIntermediaireFormate() {
        String motIntermediaireFormate = "";
        // Tri par position du motIntermediaire
        for (int position = 0; position<motATrouver.getLettres().size();position++) {
            for (Lettre lettre:motIntermediaire.getLettres()) {
                if (lettre.getPosition() == position && Objects.equals(lettre.getStatut(), StatutLettre.VALIDE)) {
                    motIntermediaireFormate += lettre.getCaractere();
                }
            }
            if (!(motIntermediaireFormate.length() == position+1)) {
                motIntermediaireFormate += ".";
            }
        }
        return motIntermediaireFormate;
    }
}

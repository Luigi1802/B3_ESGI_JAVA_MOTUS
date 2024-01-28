package fr.esgi.service.impl;

import fr.esgi.business.Mot;
import fr.esgi.business.Lettre;
import fr.esgi.service.MotService;
import fr.esgi.utils.ComparateurLettreParPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MotServiceImpl implements MotService{
    private static Mot motSaisi;
    private static Mot motATrouver;
    private static Mot motIntermediaire;

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


}

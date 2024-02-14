package fr.esgi.service;

import fr.esgi.business.Mot;
public interface MotService {
    Mot retournerStringEnMot(String stringMot);
    void comparateurMotsSaisiATrouver();
    boolean testerValiditeMotSaisi();
    void setMotATrouver(Mot motATrouver);
    void setMotIntermediaire(Mot motIntermediaire);
    void setMotSaisi(Mot motSaisi);
    Mot getMotSaisi();
    Mot getMotIntermediaire();
    Mot getMotATrouver();
    String retournerMotIntermediaireFormate();
}

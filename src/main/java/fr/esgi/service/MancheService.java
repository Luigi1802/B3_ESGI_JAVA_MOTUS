package fr.esgi.service;

import fr.esgi.business.Manche;

public interface MancheService {
    void setManche(Manche manche);
    Manche getManche();
    Manche lancerNouvelleManche(int numManche);

}

package fr.esgi.service;

import fr.esgi.business.Manche;

import java.io.IOException;

public interface MancheService {
    void setManche(Manche manche);
    Manche getManche();
    Manche lancerNouvelleManche(int numManche) throws IOException;

}

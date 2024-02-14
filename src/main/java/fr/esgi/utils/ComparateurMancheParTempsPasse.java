package fr.esgi.utils;

import fr.esgi.business.Manche;
import fr.esgi.service.MancheService;
import fr.esgi.service.impl.MancheServiceImpl;

import java.util.Comparator;

public class ComparateurMancheParTempsPasse implements Comparator<Manche> {
    private static MancheService mancheService = new MancheServiceImpl();

    @Override
    public int compare(Manche manche1, Manche manche2) {
        return Long.compare(mancheService.calculerTempsPasse(manche1), mancheService.calculerTempsPasse(manche2));
    }
}
package fr.esgi.service;

import java.util.ArrayList;
import java.util.stream.Stream;

public interface ImportMotsService {

    Stream<String> retournerImportMot(String urlPath);
    void creerListeMots();
    ArrayList<String> transformerStreamStringEnListe(Stream<String> streamATransformer);
    ArrayList<String> recupererListeMots();
}

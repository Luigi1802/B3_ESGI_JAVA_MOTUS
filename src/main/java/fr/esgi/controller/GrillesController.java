package fr.esgi.controller;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import fr.esgi.business.Lettre;
import fr.esgi.business.Mot;
import fr.esgi.business.StatutLettre;
import fr.esgi.service.DictionnaireService;
import fr.esgi.service.impl.DictionnaireServiceImpl;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import fr.esgi.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;

import fr.esgi.service.PartieService;
import fr.esgi.service.impl.PartieServiceImpl;
import fr.esgi.service.MancheService;
import fr.esgi.service.impl.MancheServiceImpl;
import fr.esgi.service.MotService;
import fr.esgi.service.impl.MotServiceImpl;

import static java.lang.Thread.sleep;
import static javafx.scene.input.KeyCode.A;

public class GrillesController implements Initializable {

    private static PartieService partieService = new PartieServiceImpl();
    private static MancheService mancheService = new MancheServiceImpl();
    private static MotService motService = new MotServiceImpl();
    private static DictionnaireService dictionnaireService = new DictionnaireServiceImpl();

    // Couleur de fond
    private static final BackgroundFill BACKGROUND_FILL_BLEU = new BackgroundFill(Color.web("#177E89"), null, null);
    private static final Background BACKGROUND_BLEU = new Background(BACKGROUND_FILL_BLEU);
    private static final BackgroundFill BACKGROUND_FILL_JAUNE = new BackgroundFill(Color.web("#F7B735"), new CornerRadii(30d), null);
    private static final Background BACKGROUND_JAUNE = new Background(BACKGROUND_FILL_JAUNE);
    private static final BackgroundFill BACKGROUND_FILL_ROUGE = new BackgroundFill(Color.web("#DB3A34"), null, null);
    private static final Background BACKGROUND_ROUGE = new Background(BACKGROUND_FILL_ROUGE);

    // Couleur de fond clavier
    private static final BackgroundFill KEY_BACKGROUND_FILL_ROUGE = new BackgroundFill(Color.web("#DB3A34"), new CornerRadii(10d), null);
    private static final Background KEY_BACKGROUND_ROUGE = new Background(KEY_BACKGROUND_FILL_ROUGE);
    private static final BackgroundFill KEY_BACKGROUND_FILL_JAUNE = new BackgroundFill(Color.web("#F7B735"), new CornerRadii(10d), null);
    private static final Background KEY_BACKGROUND_JAUNE = new Background(KEY_BACKGROUND_FILL_JAUNE);

    // Id Grille6 : pane et labels
    @FXML
    private Pane pane6;
    @FXML
    private  Label G611,G612,G613,G614,G615,G616;
    @FXML
    private  Label G621,G622,G623,G624,G625,G626;
    @FXML
    private  Label G631,G632,G633,G634,G635,G636;
    @FXML
    private  Label G641,G642,G643,G644,G645,G646;
    @FXML
    private  Label G651,G652,G653,G654,G655,G656;
    @FXML
    private  Label G661,G662,G663,G664,G665,G666;

    // Id Grille7 : pane et labels
    @FXML
    private Pane pane7;
    @FXML
    private  Label G711,G712,G713,G714,G715,G716,G717;
    @FXML
    private  Label G721,G722,G723,G724,G725,G726,G727;
    @FXML
    private  Label G731,G732,G733,G734,G735,G736,G737;
    @FXML
    private  Label G741,G742,G743,G744,G745,G746,G747;
    @FXML
    private  Label G751,G752,G753,G754,G755,G756,G757;
    @FXML
    private  Label G761,G762,G763,G764,G765,G766,G767;

    // Id Grille8 : pane et labels
    @FXML
    private Pane pane8;
    @FXML
    private  Label G811,G812,G813,G814,G815,G816,G817,G818;
    @FXML
    private  Label G821,G822,G823,G824,G825,G826,G827,G828;
    @FXML
    private  Label G831,G832,G833,G834,G835,G836,G837,G838;
    @FXML
    private  Label G841,G842,G843,G844,G845,G846,G847,G848;
    @FXML
    private  Label G851,G852,G853,G854,G855,G856,G857,G858;
    @FXML
    private  Label G861,G862,G863,G864,G865,G866,G867,G868;

    @FXML
    private Button suppr, entrer;
    @FXML
    private Button A,Z,E,R,T,Y,U,I,O,P,Q,S,D,F,G,H,J,K,L,M,W,X,C,V,B,N;

    // Grille de lignes
    ArrayList<ArrayList<Label>> grille = new ArrayList<ArrayList<Label>>();
    // Lignes de label (fxml) pour liage des id
    ArrayList<Label> ligne1= new ArrayList<>();
    ArrayList<Label> ligne2= new ArrayList<>();
    ArrayList<Label> ligne3= new ArrayList<>();
    ArrayList<Label> ligne4= new ArrayList<>();
    ArrayList<Label> ligne5= new ArrayList<>();
    ArrayList<Label> ligne6= new ArrayList<>();

    // Liste des touches saisies
    ArrayList<String> lettres = new ArrayList<String>();
    //index
    int colonne;
    int ligne;

    // Placement de la case sur la grille
    Label caseGrille;

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("menu");
    }

    // Initialisation des id
    public void initialize(URL location, ResourceBundle resources) {
        // Activation saisie clavier
        Platform.runLater(() -> suppr.requestFocus());

        System.out.println(motService.getMotATrouver().retournerMotEnString());
        System.out.println(motService.getMotIntermediaire().retournerMotEnString());

        // Initialisation grille
        initialiserGrille();
    }

    public void initialiserGrille() {
        // Grille selon longueur du mot
        switch (motService.getMotATrouver().getLettres().size()) {
            case 6:
                // Affichage grille longueur 6
                afficherPane(true, false, false);
                // Liage des id dans les lignes
                ligne1.add(G611); ligne1.add(G612); ligne1.add(G613); ligne1.add(G614); ligne1.add(G615); ligne1.add(G616);
                ligne2.add(G621); ligne2.add(G622); ligne2.add(G623); ligne2.add(G624); ligne2.add(G625); ligne2.add(G626);
                ligne3.add(G631); ligne3.add(G632); ligne3.add(G633); ligne3.add(G634); ligne3.add(G635); ligne3.add(G636);
                ligne4.add(G641); ligne4.add(G642); ligne4.add(G643); ligne4.add(G644); ligne4.add(G645); ligne4.add(G646);
                ligne5.add(G651); ligne5.add(G652); ligne5.add(G653); ligne5.add(G654); ligne5.add(G655); ligne5.add(G656);
                ligne6.add(G661); ligne6.add(G662); ligne6.add(G663); ligne6.add(G664); ligne6.add(G665); ligne6.add(G666);
                // Ajout dans notre grille a deux dimension
                ajouterLignesdansGrille();
                break;
            case 7:
                // Affichage grille longueur 7
                afficherPane(false, true, false);
                // Liage des id dans les lignes
                ligne1.add(G711); ligne1.add(G712); ligne1.add(G713); ligne1.add(G714); ligne1.add(G715); ligne1.add(G716); ligne1.add(G717);
                ligne2.add(G721); ligne2.add(G722); ligne2.add(G723); ligne2.add(G724); ligne2.add(G725); ligne2.add(G726); ligne2.add(G727);
                ligne3.add(G731); ligne3.add(G732); ligne3.add(G733); ligne3.add(G734); ligne3.add(G735); ligne3.add(G736); ligne3.add(G737);
                ligne4.add(G741); ligne4.add(G742); ligne4.add(G743); ligne4.add(G744); ligne4.add(G745); ligne4.add(G746); ligne4.add(G747);
                ligne5.add(G751); ligne5.add(G752); ligne5.add(G753); ligne5.add(G754); ligne5.add(G755); ligne5.add(G756); ligne5.add(G757);
                ligne6.add(G761); ligne6.add(G762); ligne6.add(G763); ligne6.add(G764); ligne6.add(G765); ligne6.add(G766); ligne6.add(G767);
                // Ajout dans notre grille a deux dimension
                ajouterLignesdansGrille();
                break;
            case 8:
                // Affichage grille longueur 8
                afficherPane(false, false, true);
                // Liage des id dans les lignes
                ligne1.add(G811); ligne1.add(G812); ligne1.add(G813); ligne1.add(G814); ligne1.add(G815); ligne1.add(G816); ligne1.add(G817); ligne1.add(G818);
                ligne2.add(G821); ligne2.add(G822); ligne2.add(G823); ligne2.add(G824); ligne2.add(G825); ligne2.add(G826); ligne2.add(G827); ligne2.add(G828);
                ligne3.add(G831); ligne3.add(G832); ligne3.add(G833); ligne3.add(G834); ligne3.add(G835); ligne3.add(G836); ligne3.add(G837); ligne3.add(G838);
                ligne4.add(G841); ligne4.add(G842); ligne4.add(G843); ligne4.add(G844); ligne4.add(G845); ligne4.add(G846); ligne4.add(G847); ligne4.add(G848);
                ligne5.add(G851); ligne5.add(G852); ligne5.add(G853); ligne5.add(G854); ligne5.add(G855); ligne5.add(G856); ligne5.add(G857); ligne5.add(G858);
                ligne6.add(G861); ligne6.add(G862); ligne6.add(G863); ligne6.add(G864); ligne6.add(G865); ligne6.add(G866); ligne6.add(G867); ligne6.add(G868);
                // Ajout dans notre grille a deux dimension
                ajouterLignesdansGrille();
                break;
            default:
                System.out.println("Taille du mot incorrect");
                break;
        }
        // Depart sur la premiere ligne ; deuxième lettre
        ligne = 0;
        colonne = 1;
        // Initialisation motSaisi avec premiere lettre de motATrouver
        lettres.add(motService.getMotATrouver().getLettres().get(0).getCaractere().toString());
        // Affichage premiere ligne
        afficherLigneIntermediaire();
    }

    public void afficherPane(boolean p6, boolean p7, boolean p8) {
        pane6.setVisible(p6);
        pane7.setVisible(p7);
        pane8.setVisible(p8);
    }

    public void ajouterLignesdansGrille() {
        // Ajout dans notre tableau a deux dmension
        grille.add(ligne1);
        grille.add(ligne2);
        grille.add(ligne3);
        grille.add(ligne4);
        grille.add(ligne5);
        grille.add(ligne6);
    }

    public void afficherLigneIntermediaire() {
        String motInterString = motService.retournerMotIntermediaireFormate();
        for (int i = 0; i < motInterString.length(); i++) {
            grille.get(ligne).get(i).setText(String.valueOf(motInterString.charAt(i)).toUpperCase());
            if (i == 0){
                grille.get(ligne).get(i).setBackground(BACKGROUND_BLEU);
            } else {
                grille.get(ligne).get(i).setBackground(null);
            }
        }
    }

    public void afficherLigneVerifiee() {
        for (Lettre lettre:motService.getMotSaisi().getLettres()) {
            switch (lettre.getStatut()) {
                case VALIDE:
                    grille.get(ligne).get(lettre.getPosition()).setBackground(BACKGROUND_ROUGE);
                    break;
                case TROUVE:
                    grille.get(ligne).get(lettre.getPosition()).setBackground(BACKGROUND_JAUNE);
                    break;
                default:
                    grille.get(ligne).get(lettre.getPosition()).setBackground(null);
                    break;
            }
        }
    }

    public void mettreAJourClavier(){
        for (Lettre lettre:motService.getMotSaisi().getLettres()) {
            switch (lettre.getCaractere()) {
                case 'a':
                    changerVisuelTouche(A, lettre.getStatut());
                    break;
                case 'b':
                    changerVisuelTouche(B, lettre.getStatut());
                    break;
                case 'c':
                    changerVisuelTouche(C, lettre.getStatut());
                    break;
                case 'd':
                    changerVisuelTouche(D, lettre.getStatut());
                    break;
                case 'e':
                    changerVisuelTouche(E, lettre.getStatut());
                    break;
                case 'f':
                    changerVisuelTouche(F, lettre.getStatut());
                    break;
                case 'g':
                    changerVisuelTouche(G, lettre.getStatut());
                    break;
                case 'h':
                    changerVisuelTouche(H, lettre.getStatut());
                    break;
                case 'i':
                    changerVisuelTouche(I, lettre.getStatut());
                    break;
                case 'j':
                    changerVisuelTouche(J, lettre.getStatut());
                    break;
                case 'k':
                    changerVisuelTouche(K, lettre.getStatut());
                    break;
                case 'l':
                    changerVisuelTouche(L, lettre.getStatut());
                    break;
                case 'm':
                    changerVisuelTouche(M, lettre.getStatut());
                    break;
                case 'n':
                    changerVisuelTouche(N, lettre.getStatut());
                    break;
                case 'o':
                    changerVisuelTouche(O, lettre.getStatut());
                    break;
                case 'p':
                    changerVisuelTouche(P, lettre.getStatut());
                    break;
                case 'q':
                    changerVisuelTouche(Q, lettre.getStatut());
                    break;
                case 'r':
                    changerVisuelTouche(R, lettre.getStatut());
                    break;
                case 's':
                    changerVisuelTouche(S, lettre.getStatut());
                    break;
                case 't':
                    changerVisuelTouche(T, lettre.getStatut());
                    break;
                case 'u':
                    changerVisuelTouche(U, lettre.getStatut());
                    break;
                case 'v':
                    changerVisuelTouche(V, lettre.getStatut());
                    break;
                case 'w':
                    changerVisuelTouche(W, lettre.getStatut());
                    break;
                case 'x':
                    changerVisuelTouche(X, lettre.getStatut());
                    break;
                case 'y':
                    changerVisuelTouche(Y, lettre.getStatut());
                    break;
                case 'z':
                    changerVisuelTouche(Z, lettre.getStatut());
                    break;
            }
        }
    }

    public void changerVisuelTouche(Button bouton, StatutLettre statut) {
        switch (statut) {
            case VALIDE:
                bouton.setBackground(KEY_BACKGROUND_ROUGE);
                //bouton.setStyle("-fx-focus-traversable: false;");
                break;
            case TROUVE:
                if (bouton.getBackground() != KEY_BACKGROUND_ROUGE) {
                    bouton.setBackground(KEY_BACKGROUND_JAUNE);
                    //bouton.setStyle("-fx-focus-traversable: false;");
                }
                break;
            case ABSENTE:
                if (bouton.getBackground() != KEY_BACKGROUND_ROUGE && bouton.getBackground() != KEY_BACKGROUND_JAUNE) {
                    bouton.setOpacity(0.3);
                }
                break;
        }
    }

    public Mot convertirLettresEnMot() {
        String lettresString = new String();
        for (String lettre:lettres) {
            lettresString += lettre.toLowerCase();
        }
        return motService.retournerStringEnMot(lettresString);
    }

    public void validerSaisie() {
        Mot motSaisiInterface = convertirLettresEnMot();
        motService.setMotSaisi(motSaisiInterface);
        // Test si mot saisi est dans le dictionnaire
        if (dictionnaireService.testerMotPresentDictionnaire(motService.getMotSaisi().retournerMotEnString())) {
            //methode comparaison + nouvelle ligne
            mancheService.getManche().ajouterEssai();
            // Comparaison motSaisi - motATrouver
            motService.comparateurMotsSaisiATrouver();
            // Affichage résultat de la saisie précédente
            afficherLigneVerifiee();
            // Mise a jour du clavier
            mettreAJourClavier();
            // Test victoire
            if (motService.testerValiditeMotSaisi()) {
                mancheService.getManche().setHeureFin(LocalDateTime.now());
                mancheService.getManche().setVictoire(true);
                // TODO CHANGEMENT DE MANCHE
            } else {
                // Remise à blanc de lettres SAUF la première
                lettres.subList(1, lettres.size()).clear();
                ++ligne;
                colonne = 1;
                afficherLigneIntermediaire();
            }
        } else {
            // Remise à blanc de la ligne et repositionnement case
            // Remise à blanc de lettres SAUF la première
            lettres.subList(1, lettres.size()).clear();
            colonne = 1;
            afficherLigneIntermediaire();
        }
    }

    // Saisie de lettre bouton
    @FXML
    public void saisirLettre(ActionEvent actionEvent) {
        caseGrille = grille.get(ligne).get(colonne);
        Button boutonSource = (Button) actionEvent.getSource();
        String boutonLettre = boutonSource.getText();

        if (!(colonne == motService.getMotATrouver().getLettres().size())) {
            caseGrille.setText(boutonLettre);
            caseGrille.setBackground(BACKGROUND_BLEU);
            lettres.add(caseGrille.getText());
            ++colonne;
        }
        // LOG
        System.out.println("motSaisi " + lettres);
        System.out.println("motatrouver len: " + motService.getMotATrouver().getLettres().size());
        System.out.println("lettres len: " + lettres.size());
        System.out.println( "ligne " + ligne + " colonne "+ colonne);
    }

    // Bouton Supprimer
    @FXML
    public void supprimerLettre(ActionEvent actionEvent){
        if (colonne > 1){
            colonne = colonne - 1;
            caseGrille = grille.get(ligne).get(colonne);
            caseGrille.setText(".");
            caseGrille.setBackground(null);
            lettres.remove(colonne);
        }
        // LOG
        System.out.println("motSaisi " + lettres);
        System.out.println( "ligne " + ligne + " colonne "+ colonne);
    }

    // Bouton entrer
    @FXML
    public void entrer(ActionEvent actionEvent){
        if (colonne == motService.getMotATrouver().getLettres().size() && ligne < 6){
            validerSaisie();
        }
        // TODO enlever LOG
        System.out.println("motSaisi " + lettres);
        System.out.println( "ligne " + ligne + " colonne "+ colonne);
    }

    @FXML
    public void saisirToucheClavier(KeyEvent keyEvent) {
        // La case a modifier
        //caseGrille = grille.get(ligne).get(colonne);

        // Gestion des touches
        switch (keyEvent.getCode()) {
            case BACK_SPACE:
                if (colonne > 1){
                    colonne = colonne -1;
                    caseGrille = grille.get(ligne).get(colonne);
                    caseGrille.setText(".");
                    caseGrille.setBackground(null);
                    lettres.remove(colonne);
                }
                break;
            case ENTER:
                if (colonne == motService.getMotATrouver().getLettres().size() && ligne < 6){
                    validerSaisie();
                }
                break;
            // Lettres
            default:
                if (keyEvent.getCode().toString().matches("[a-zA-Z]")) {
                    System.out.println("detection default: " + keyEvent.getCode());
                    caseGrille = grille.get(ligne).get(colonne);
                    caseGrille.setText(keyEvent.getCode().toString());
                    lettres.add(caseGrille.getText());
                    caseGrille.setBackground(BACKGROUND_BLEU);
                    ++colonne;
                }
                break;
        }
        // LOG
        System.out.println("detection touche: " + keyEvent.getCode());
        System.out.println("motSaisi " + lettres);
        System.out.println( "ligne " + ligne + " colonne "+ colonne);
    }

}
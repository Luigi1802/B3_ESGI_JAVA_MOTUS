package fr.esgi.controller;

import java.io.IOException;
import java.net.URL;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import fr.esgi.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.input.KeyEvent;

import fr.esgi.service.PartieService;
import fr.esgi.service.impl.PartieServiceImpl;
import fr.esgi.service.MancheService;
import fr.esgi.service.impl.MancheServiceImpl;
import fr.esgi.service.MotService;
import fr.esgi.service.impl.MotServiceImpl;

public class GrillesController implements Initializable {

    private static PartieService partieService = new PartieServiceImpl();
    private static MancheService mancheService = new MancheServiceImpl();
    private static MotService motService = new MotServiceImpl();

    // Couleur de fond
    private static final BackgroundFill BACKGROUND_FILL_BLEU = new BackgroundFill(Color.web("#177E89"), null, null);
    private static final Background BACKGROUND_BLEU = new Background(BACKGROUND_FILL_BLEU);
    private static final BackgroundFill BACKGROUND_FILL_JAUNE = new BackgroundFill(Color.web("#F7B735"), null, null);
    private static final Background BACKGROUND_JAUNE = new Background(BACKGROUND_FILL_JAUNE);
    private static final BackgroundFill BACKGROUND_FILL_ROUGE = new BackgroundFill(Color.web("#DB3A34"), null, null);
    private static final Background BACKGROUND_ROUGE = new Background(BACKGROUND_FILL_ROUGE);

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
    ArrayList<Character> lettres = new ArrayList<Character>();
    //index
    int colonne=1;
    int ligne;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    // Initialisation des id
    public void initialize(URL location, ResourceBundle resources) {
        // Lancement de la partie
        //partieService.lancerNouvellePartie();

        // DEV
        // Lancement de la manche
        mancheService.lancerNouvelleManche(1);
        // log
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
                pane6.setVisible(true);
                pane7.setVisible(false);
                pane8.setVisible(false);
                // Liage des id dans les lignes
                ligne1.add(G611); ligne1.add(G612); ligne1.add(G613); ligne1.add(G614); ligne1.add(G615); ligne1.add(G616);
                ligne2.add(G621); ligne2.add(G622); ligne2.add(G623); ligne2.add(G624); ligne2.add(G625); ligne2.add(G626);
                ligne3.add(G631); ligne3.add(G632); ligne3.add(G633); ligne3.add(G634); ligne3.add(G635); ligne3.add(G636);
                ligne4.add(G641); ligne4.add(G642); ligne4.add(G643); ligne4.add(G644); ligne4.add(G645); ligne4.add(G646);
                ligne5.add(G651); ligne5.add(G652); ligne5.add(G653); ligne5.add(G654); ligne5.add(G655); ligne5.add(G656);
                ligne6.add(G661); ligne6.add(G662); ligne6.add(G663); ligne6.add(G664); ligne6.add(G665); ligne6.add(G666);
                // Ajout dans notre grille a deux dimension
                grille.add(ligne1);
                grille.add(ligne2);
                grille.add(ligne3);
                grille.add(ligne4);
                grille.add(ligne5);
                grille.add(ligne6);
                break;
            case 7:
                // Affichage grille longueur 7
                pane6.setVisible(false);
                pane7.setVisible(true);
                pane8.setVisible(false);
                // Liage des id dans les lignes
                ligne1.add(G711); ligne1.add(G712); ligne1.add(G713); ligne1.add(G714); ligne1.add(G715); ligne1.add(G716); ligne1.add(G717);
                ligne2.add(G721); ligne2.add(G722); ligne2.add(G723); ligne2.add(G724); ligne2.add(G725); ligne2.add(G726); ligne2.add(G727);
                ligne3.add(G731); ligne3.add(G732); ligne3.add(G733); ligne3.add(G734); ligne3.add(G735); ligne3.add(G736); ligne3.add(G737);
                ligne4.add(G741); ligne4.add(G742); ligne4.add(G743); ligne4.add(G744); ligne4.add(G745); ligne4.add(G746); ligne4.add(G747);
                ligne5.add(G751); ligne5.add(G752); ligne5.add(G753); ligne5.add(G754); ligne5.add(G755); ligne5.add(G756); ligne5.add(G757);
                ligne6.add(G761); ligne6.add(G762); ligne6.add(G763); ligne6.add(G764); ligne6.add(G765); ligne6.add(G766); ligne6.add(G767);
                // Ajout dans notre grille a deux dimension
                grille.add(ligne1);
                grille.add(ligne2);
                grille.add(ligne3);
                grille.add(ligne4);
                grille.add(ligne5);
                grille.add(ligne6);
                break;
            case 8:
                // Ajout dans notre grille a deux dimension
                pane6.setVisible(false);
                pane7.setVisible(false);
                pane8.setVisible(true);
                // Liage des id dans les lignes
                ligne1.add(G811); ligne1.add(G812); ligne1.add(G813); ligne1.add(G814); ligne1.add(G815); ligne1.add(G816); ligne1.add(G817); ligne1.add(G818);
                ligne2.add(G821); ligne2.add(G822); ligne2.add(G823); ligne2.add(G824); ligne2.add(G825); ligne2.add(G826); ligne2.add(G827); ligne2.add(G828);
                ligne3.add(G831); ligne3.add(G832); ligne3.add(G833); ligne3.add(G834); ligne3.add(G835); ligne3.add(G836); ligne3.add(G837); ligne3.add(G838);
                ligne4.add(G841); ligne4.add(G842); ligne4.add(G843); ligne4.add(G844); ligne4.add(G845); ligne4.add(G846); ligne4.add(G847); ligne4.add(G848);
                ligne5.add(G851); ligne5.add(G852); ligne5.add(G853); ligne5.add(G854); ligne5.add(G855); ligne5.add(G856); ligne5.add(G857); ligne5.add(G858);
                ligne6.add(G861); ligne6.add(G862); ligne6.add(G863); ligne6.add(G864); ligne6.add(G865); ligne6.add(G866); ligne6.add(G867); ligne6.add(G868);
                // Ajout dans notre tableau a deux dmension
                grille.add(ligne1);
                grille.add(ligne2);
                grille.add(ligne3);
                grille.add(ligne4);
                grille.add(ligne5);
                grille.add(ligne6);
                break;
            default:
                System.out.println("Taille du mot incorrect");
                break;
        }
        // Depart sur la premiere ligne
        ligne = 0;
        // Affichage premiere ligne
        afficherPremiereLigne();

    }

    public void afficherPremiereLigne() {
        // Première lettre suivie de '.'
        ligne1.get(0).setText(String.valueOf(motService.getMotATrouver().getLettres().get(0).getCaractere()).toUpperCase());
        for (int i = 1; i < ligne1.size(); i++) {
            ligne1.get(i).setText(".");
        }
    }

    // Saisie de lettre
    @FXML
    public void saisirLettre(ActionEvent actionEvent) {
        Label caseGrille = grille.get(ligne).get(colonne);
        Button boutonSource = (Button) actionEvent.getSource();
        String boutonLettre = boutonSource.getText();

        caseGrille.setText(boutonLettre);
        caseGrille.setBackground(BACKGROUND_BLEU);
        lettres.add(caseGrille.getText().charAt(0));
        ++colonne;
    }

    @FXML
    public void saisirLettreClavier(KeyEvent keyEvent) {
        String lettreClavier = keyEvent.getCharacter();
        // Lettre seulement
        if (lettreClavier.matches("[a-zA-Z]")) {
            Label caseGrille= grille.get(ligne).get(colonne);
            caseGrille.setText(lettreClavier.toUpperCase());
            caseGrille.setBackground(BACKGROUND_BLEU);
            lettres.add(lettreClavier.charAt(0));
            ++colonne;
        }
    }

    // Suppression et validation
    @FXML
    public void supprimerLettre(ActionEvent actionEvent){
        if (colonne>1){
            Label caseGrille;
            colonne = colonne-1;
            caseGrille = grille.get(ligne).get(colonne);
            caseGrille.setText(".");
            caseGrille.setBackground(null);
            lettres.remove(colonne);
        }

    }

    // TODO faire fonctionner
    @FXML
    public void supprimerLettreClavier(KeyEvent keyEvent) {
        System.out.println("Touche pressée: " + keyEvent.getCode());
        if (keyEvent.getCode() == KeyCode.BACK_SPACE || keyEvent.getCode() == KeyCode.DELETE) {
            System.out.println("BACK_SPACE détecté");
            System.out.println("wsh");
            if (colonne>1) {
                Label caseGrille;
                colonne = colonne-1;
                caseGrille = grille.get(ligne).get(colonne);
                caseGrille.setText(".");
                caseGrille.setBackground(null);
                lettres.remove(colonne);
                // System.out.println(lettres);
            }
        }
    }

    @FXML
    public void entrer(ActionEvent actionEvent){
        // TODO boucle de traitement
        // Si mot valide -> ligne suivante
        // TODO afficher mot intermediaire
        if (ligne < 5) {
            ++ligne;
            colonne = 1;
        }
    }

    // TODO faire fonctionner
    @FXML
    public void entrerClavier(KeyEvent keyEvent) {
        System.out.println("Touche pressée: " + keyEvent.getCode());
        if (keyEvent.getCode() == KeyCode.ENTER) {
            System.out.println("ENTER détecté");
            // TODO boucle de traitement
            // Si mot valide -> ligne suivante
            // TODO afficher mot intermediaire
            if (ligne < 5) {
                ++ligne;
                colonne = 1;
            }
        }
    }
}

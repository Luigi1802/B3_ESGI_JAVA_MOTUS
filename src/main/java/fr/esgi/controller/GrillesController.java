package fr.esgi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.esgi.service.impl.MotServiceImpl;
import javafx.fxml.Initializable;
import fr.esgi.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import fr.esgi.service.MotService;


public class GrillesController implements Initializable {

    private static MotService motService = new MotServiceImpl();

    // id Grille6 : pane et labels
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

    // id Grille7 : pane et labels
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

    // id Grille8 : pane et labels
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

    // Couleur de fond
    BackgroundFill backgroundFillBleu = new BackgroundFill(Color.web("#177E89"),null,null);
    Background backgroundBleu = new Background(backgroundFillBleu);


    ArrayList<Character> lettres = new ArrayList<Character>();
    int colone=1;
    int ligne=0;
    ArrayList<Label> ligne1= new ArrayList<>();
    ArrayList<Label> ligne2= new ArrayList<>();
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    //Test echange avec back pour premiere lettre
    public void startJeu(Character premiereLettre){

        lettres.add(premiereLettre);
        G711.setText(premiereLettre.toString());
        System.out.println("premiere lettre"+premiereLettre);
    }

    // Initialisation des id
    public void initialize(URL location, ResourceBundle resources) {

        ligne1.add(G711);
        ligne1.add(G712);
        ligne1.add(G713);
        ligne1.add(G714);
        ligne1.add(G715);
        ligne1.add(G716);
        ligne1.add(G717);

        // DEV
        pane6.setVisible(true);
        pane7.setVisible(true);
        pane8.setVisible(true);

    }

    @FXML
    public void ajouterA(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("A");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('A');
        colone= colone+1;
    }

    @FXML
    public void ajouterZ(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("Z");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('Z');
        colone= colone+1;

    }
    @FXML
    public void ajouterE(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("E");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('E');
        colone= colone+1;

    }
    @FXML
    public void ajouterR(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("R");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('R');
        colone= colone+1;

    }
    @FXML
    public void ajouterT(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("T");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('T');
        colone= colone+1;

    }
    @FXML
    public void ajouterY(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("Y");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('Y');
        colone= colone+1;

    }
    @FXML
    public void ajouterU(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("U");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('U');
        colone= colone+1;

    }
    @FXML
    public void ajouterI(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("I");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('I');
        colone= colone+1;

    }
    @FXML
    public void ajouterO(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("O");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('O');
        colone= colone+1;

    }
    @FXML
    public void ajouterP(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("P");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('P');
        colone= colone+1;

    }
    @FXML
    public void ajouterQ(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("Q");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('Q');
        colone= colone+1;

    }
    @FXML
    public void ajouterS(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("S");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('S');
        colone= colone+1;

    }
    @FXML
    public void ajouterD(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("D");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('D');
        colone= colone+1;

    }
    @FXML
    public void ajouterF(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("F");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('F');
        colone= colone+1;

    }
    @FXML
    public void ajouterG(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("G");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('G');
        colone= colone+1;

    }
    @FXML
    public void ajouterH(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("H");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('H');
        colone= colone+1;

    }
    @FXML
    public void ajouterJ(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("J");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('J');
        colone= colone+1;

    }
    @FXML
    public void ajouterK(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("K");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('K');
        colone= colone+1;

    }
    @FXML
    public void ajouterL(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("L");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('L');
        colone= colone+1;

    }
    @FXML
    public void ajouterM(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("M");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('M');
        colone= colone+1;

    }
    @FXML
    public void ajouterW(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("W");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('W');
        colone= colone+1;

    }
    @FXML
    public void ajouterX(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("X");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('X');
        colone= colone+1;

    }
    @FXML
    public void ajouterC(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("C");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('C');
        colone= colone+1;

    }
    @FXML
    public void ajouterV(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("V");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('V');
        colone= colone+1;

    }
    @FXML
    public void ajouterB(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("B");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('B');
        colone= colone+1;

    }
    @FXML
    public void ajouterN(ActionEvent actionEvent){
        Label labelEnCours= new Label();
        labelEnCours=ligne1.get(colone);
        labelEnCours.setText("N");
        labelEnCours.setBackground(backgroundBleu);
        lettres.add('N');
        colone= colone+1;

    }

    @FXML
    public void suppr(ActionEvent actionEvent){
        if (colone>1){
            Label labelASuppr = new Label();
            colone=colone-1;
            labelASuppr = ligne1.get(colone);
            // TODO Gerer le point !
           //labelASuppr.setText(".");
            labelASuppr.setBackground(null);
            labelASuppr.setText("");
            lettres.remove(colone);
            System.out.println(lettres);

        }

    }

    @FXML
    public void entrer(ActionEvent actionEvent){
        // TODO validation du mot et traitement
    }


}

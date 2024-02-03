package fr.esgi.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.esgi.service.impl.MotServiceImpl;
import javafx.css.converter.ColorConverter;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import fr.esgi.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import fr.esgi.business.Mot;
import fr.esgi.service.MotService;


public class PrimaryController implements Initializable {

    private static MotService motService = new MotServiceImpl();
    BackgroundFill backgroundFillBleu = new BackgroundFill(Color.web("#177E89"),null,null);
    Background backgroundBleu = new Background(backgroundFillBleu);

    ArrayList<Character> lettres = new ArrayList<Character>();
    int colone=1;
    int ligne=0;

    @FXML
    private Pane Pane7;

    @FXML
    private  Label G11,G12,G13,G14,G15,G16,G17,G18;

    @FXML
    private  Label G21,G22,G23,G24,G25,G26,G27,G28;

    @FXML
    private Label G111;

    ArrayList<Label> ligne1= new ArrayList<>();
    ArrayList<Label> ligne2= new ArrayList<>();
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    //Test echange avec back pour premiere lettre
    public void startJeu(Character premiereLettre){

        lettres.add(premiereLettre);
        G11.setText(premiereLettre.toString());
        System.out.println("premiere lettre"+premiereLettre);
    }
    public void initialize(URL location, ResourceBundle resources) {

        ligne1.add(G11);
        ligne1.add(G12);
        ligne1.add(G13);
        ligne1.add(G14);
        ligne1.add(G15);
        ligne1.add(G16);
        ligne1.add(G17);
        ligne1.add(G18);

        ligne2.add(G21);
        ligne2.add(G22);
        ligne2.add(G23);
        ligne2.add(G24);
        ligne2.add(G25);
        ligne2.add(G26);
        ligne2.add(G27);
        ligne2.add(G28);
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


}

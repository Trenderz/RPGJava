package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import main.java.models.*;
import main.java.utils.Constante;
import javafx.scene.image.ImageView;

public class FinCombatController {

    private CombatController parent;
    private Personnage personnage;
    private Personnage ennemi;

    @FXML
    private Label piecesRemportees;

    @FXML
    private Label piecesPersonnage1;

    @FXML
    private Label piecesPersonnage2;

    @FXML
    private Label piecesPersonnage3;

    // tab boutique sort
    @FXML
    private ImageView imageSort1;
    @FXML
    private Label nomSort1;
    @FXML
    private Button btnSort1;

    @FXML
    private ImageView imageSort2;
    @FXML
    private Label nomSort2;
    @FXML
    private Button btnSort2;

    @FXML
    private ImageView imageSort3;
    @FXML
    private Label nomSort3;
    @FXML
    private Button btnSort3;

    private Sort sort1 = new VoleeFleches();
    private Sort sort2 = new CriDeGuerre();
    private Sort sort3 = new ExplosionDeFeu();


    public void setParent(CombatController parent) {
        this.parent = parent;
    }

    public void init(Personnage personnage, Personnage ennemi){
        this.personnage = personnage;
        this.ennemi = ennemi;
        float pieces = ennemi.getNiv() * 25;
        this.personnage.setPieces(this.personnage.getPieces() + pieces);
        this.piecesRemportees.setText("+ " + pieces + " pièces");
        this.piecesPersonnage1.setText(this.personnage.getPieces() + " pièces au total");
        this.piecesPersonnage2.setText(this.personnage.getPieces() + " pièces au total");
        this.piecesPersonnage3.setText(this.personnage.getPieces() + " pièces au total");
        this.chargerSort();
    }

    public void changerEquipement(){
        parent.changerEquipement();
    }

    public void chargerSort(){
        this.imageSort1.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort1.getUrlImage()));
        this.imageSort2.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort2.getUrlImage()));
        this.imageSort3.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort3.getUrlImage()));

        this.nomSort1.setText(this.sort1.getNom());
        this.nomSort2.setText(this.sort2.getNom());
        this.nomSort3.setText(this.sort3.getNom());

        this.btnSort1.setText(this.sort1.getPrix() + " pièces");
        this.btnSort2.setText(this.sort2.getPrix() + " pièces");
        this.btnSort3.setText(this.sort3.getPrix() + " pièces");
    }

    @FXML
    public void achatSort1(){
        achatSort(sort1);
    }

    @FXML
    public void achatSort2(){
        achatSort(sort2);
    }

    @FXML
    public void achatSort3(){
        achatSort(sort3);
    }
    private void achatSort(Sort sort) {
        if (this.personnage.getPieces() >= sort.getPrix()){
            this.personnage.ajouterSort(sort);
            this.personnage.setPieces(this.personnage.getPieces() - sort.getPrix());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(sort.getNom() + " acheté et ajouté à votre inventaire");
            alert.show();
            this.piecesPersonnage1.setText(this.personnage.getPieces() + " pièces au total");
            this.piecesPersonnage2.setText(this.personnage.getPieces() + " pièces au total");
            this.piecesPersonnage3.setText(this.personnage.getPieces() + " pièces au total");
        }
    }
}

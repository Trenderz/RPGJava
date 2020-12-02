package main.java.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import main.java.models.Arme;
import main.java.utils.Constante;

import java.util.Collections;

public class ItemEquipementArmeController {
    private SelectionEquipementController parent;
    private Arme arme;
    private boolean equipe;

    @FXML
    private HBox hBoxItem;

    @FXML
    private Button buttonSwitch;

    @FXML
    private ImageView image;

    @FXML
    private Label labelNom;

    public void setParent(SelectionEquipementController parent) {
        this.parent = parent;
    }

    public void chargerArme(Arme arme, boolean equipe) {
        image.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + arme.getUrlImage()));
        labelNom.setText(arme.getNom());
        this.arme = arme;
        this.equipe = equipe;
    }

    @FXML
    public void onSwitch() {
        if (equipe)
            parent.switchArmeVersInventaire(arme, this);
        else {
            parent.switchArmeVersEquipe(arme, this);
        }
    }

    public void passerEquiper() {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(hBoxItem.getChildren());
        Collections.swap(workingCollection, 0, 2);
        hBoxItem.getChildren().setAll(workingCollection);

        buttonSwitch.setText("<-");
        this.equipe = true;
    }

    public void passerInventaire() {
        ObservableList<Node> workingCollection = FXCollections.observableArrayList(hBoxItem.getChildren());
        Collections.swap(workingCollection, 0, 2);
        hBoxItem.getChildren().setAll(workingCollection);

        buttonSwitch.setText("->");
        this.equipe = false;
    }

    public boolean estEquipe() {
        return equipe;
    }

    public void desactiverSwitch() {
        this.buttonSwitch.setDisable(true);
    }

    public void activerSwitch() {
        this.buttonSwitch.setDisable(false);
    }
}

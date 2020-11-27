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
import main.java.models.Sort;
import main.java.utils.Constante;

import java.util.Collections;

public class ItemEquipementSortController {
    private SelectionEquipementController parent;
    private Sort sort;
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

    public void chargerSort(Sort sort, boolean equipe) {
        image.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + sort.getUrlImage()));
        labelNom.setText(sort.getNom());
        this.sort = sort;
        this.equipe = equipe;
    }

    @FXML
    public void onSwitch() {
        if (equipe)
            parent.switchSortVersInventaire(sort, this);
        else {
            parent.switchSortVersEquipe(sort, this);
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

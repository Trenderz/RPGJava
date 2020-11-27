package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.RPG;
import main.java.models.Personnage;
import main.java.utils.Constante;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class InfoPersonnageController {
    private Personnage personnage;
    private CombatController parent;

    @FXML
    private Text textPv;

    @FXML
    private ProgressBar barrePv;
    @FXML
    private ProgressBar barrePm;

    @FXML
    private Text textPm;
    @FXML
    private Text textNiv;

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
        this.personnage.getPvProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPv.setText(String.valueOf(newValue));
                    barrePv.setProgress(personnage.getPv()/personnage.getPvMaxProperty().get());
                }
        );
        this.personnage.getPmProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPm.setText(String.valueOf(newValue));
                    barrePm.setProgress(personnage.getPm()/personnage.getPmMaxProperty().get());
                }
        );
        this.personnage.getNivProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textNiv.setText(String.valueOf(newValue));
                }
        );
        this.barrePv.setProgress(personnage.getPv()/personnage.getPvMaxProperty().get());
        this.barrePm.setProgress(personnage.getPm()/personnage.getPmMaxProperty().get());
        textPv.setText(String.valueOf(personnage.getPv()));
        textPm.setText(String.valueOf(personnage.getPm()));
        textNiv.setText(String.valueOf(personnage.getNiv()));
    }

    @FXML
    public void changerEquipement(){
        parent.changerEquipement();
    }

    public void setParent(CombatController parent) {
        this.parent = parent;
    }

}

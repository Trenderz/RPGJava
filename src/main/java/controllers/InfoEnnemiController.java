package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import main.java.models.Personnage;

import java.io.IOException;

public class InfoEnnemiController {
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
    private Label infoEnnemi;

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
        this.personnage.getPvProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPv.setText(String.valueOf(newValue));
                    barrePv.setProgress(personnage.getPv()/personnage.getPvMaxProperty().get());
                    if (personnage.getPv() <= 0 ){
                      parent.ennemiVaincu();
                    }
                }
        );
        this.personnage.getPmProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPm.setText(String.valueOf(newValue));
                    barrePm.setProgress(personnage.getPm()/personnage.getPmMaxProperty().get());
                }
        );

        this.barrePv.setProgress(personnage.getPv()/personnage.getPvMaxProperty().get());
        this.barrePm.setProgress(personnage.getPm()/personnage.getPmMaxProperty().get());
        textPv.setText(String.valueOf(personnage.getPv()));
        textPm.setText(String.valueOf(personnage.getPm()));
        this.infoEnnemi.setText(this.personnage.toString());
    }

    public void setParent(CombatController parent) {
        this.parent = parent;
    }


}

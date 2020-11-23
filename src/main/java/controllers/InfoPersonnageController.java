package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
import main.java.models.Personnage;

public class InfoPersonnageController {
    private Personnage personnage;
    
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
                    barrePv.setProgress(personnage.getPm()/personnage.getPmMaxProperty().get());
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
}

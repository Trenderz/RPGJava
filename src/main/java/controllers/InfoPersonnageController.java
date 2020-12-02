package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.models.Personnage;
import main.java.utils.Constante;


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
    private Label regenPm;
    @FXML
    private Label infoPerso;

    @FXML
    private ImageView action1;
    @FXML
    private ImageView action2;
    @FXML
    private Button btnPasser;

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
        this.personnage.getPvProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPv.setText(String.valueOf(newValue));
                    barrePv.setProgress(personnage.getPv() / personnage.getPvMaxProperty().get());
                    if (personnage.getPv() <= 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Game Over");
                        alert.setContentText("Vous êtes mort ... ( faut le faire quand même)");
                        alert.setOnHidden(evt -> parent.retourEcranSelection());
                        alert.show();
                    }
                }
        );
        this.personnage.getPmProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPm.setText(String.valueOf(newValue));
                    barrePm.setProgress(personnage.getPm() / personnage.getPmMaxProperty().get());
                }
        );
        this.barrePv.setProgress(personnage.getPv() / personnage.getPvMaxProperty().get());
        this.barrePm.setProgress(personnage.getPm() / personnage.getPmMaxProperty().get());
        textPv.setText(String.valueOf(personnage.getPv()));
        textPm.setText(String.valueOf(personnage.getPm()));
        this.chargerImagesActions();
        this.regenPm.setText("+ " + this.personnage.getRegenPm() + " pm/tour");
        this.updateInfosPerso();
    }

    public void setParent(CombatController parent) {
        this.parent = parent;
    }

    public void chargerImagesActions() {
        this.action1.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.personnage.getUrlImageAction1()));
        this.action2.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.personnage.getUrlImageAction2()));
    }

    @FXML
    public void action1() {
        parent.action1();
    }

    @FXML
    public void action2() {
        parent.action2();
    }

    @FXML
    void passerTour() {
        parent.passerTour();
        this.desacActions();
    }

    public void updateInfosPerso() {
        this.infoPerso.setText(this.personnage.toString());
    }

    public void desacActions() {
        action1.setDisable(true);
        action2.setDisable(true);
        btnPasser.setDisable(true);
    }

    public void activActions() {
        action1.setDisable(false);
        action2.setDisable(false);
        btnPasser.setDisable(false);
    }
}

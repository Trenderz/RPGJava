package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.models.Personnage;
import main.java.utils.Constante;

public class InfoEnnemiController {
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
    private Label infoEnnemi;

    @FXML
    private Label regenPm;

    @FXML
    private ImageView action1;

    @FXML
    private ImageView action2;

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
        //a chaque mise a jour des points de vie du personnage l'affichage est mis a jour
        this.personnage.getPvProperty().addListener(
                (observable, oldValue, newValue) -> {
                    textPv.setText(String.valueOf(newValue));
                    float progress = personnage.getPv() / personnage.getPvMax();
                    if (progress < 0) progress = 0;
                    barrePv.setProgress(progress);
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
        this.infoEnnemi.setText(this.personnage.toString());
        this.chargerImagesActions();
        this.regenPm.setText("+ " + this.personnage.getRegenPm() + " pm/tour");
        this.updateInfosPerso();
    }

    public void chargerImagesActions() {
        this.action1.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.personnage.getUrlImageAction1()));
        this.action2.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.personnage.getUrlImageAction2()));
    }

    public void updateInfosPerso() {
        this.infoEnnemi.setText(this.personnage.toString());
    }

}

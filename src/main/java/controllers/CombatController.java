package main.java.controllers;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import main.java.RPG;
import main.java.models.Personnage;
import main.java.utils.Constante;

import java.io.IOException;

public class CombatController {
    private RPG parent;
    private Personnage personnage;

    @FXML
    private HBox vBoxInformations;

    @FXML
    private ImageView imagePersonnage;

    @FXML
    private Label labelNomPersonnage;

    public void setParent(RPG rpg) {
        this.parent = parent;
    }

    @FXML
    public void clicked() throws InterruptedException {
        RotateTransition rt = new RotateTransition(Duration.millis(500),imagePersonnage);
        rt.setByAngle(20);
        rt.setCycleCount(2);
        rt.setAutoReverse(true);
        rt.setOnFinished(e->{
            imagePersonnage.setRotate(0);
            imagePersonnage.setTranslateX(0);
            imagePersonnage.setTranslateY(0);
        });
        TranslateTransition tt = new TranslateTransition(Duration.millis(500),imagePersonnage);
        tt.setByX(40);
        tt.setByY(-40);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);
        tt.play();
        rt.play();
        personnage.recevoirDegats(50);
    }

    public void initialiser(Personnage personnage) {
        this.personnage = personnage;
        this.labelNomPersonnage.setText(personnage.getNom());
        this.chargerImage(personnage);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/InfoPersonnage.fxml"));
            GridPane infosPerso = loader.load();
            InfoPersonnageController controller = loader.getController();
            controller.setPersonnage(this.personnage);
            this.vBoxInformations.getChildren().add(infosPerso);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargerImage(Personnage personnage) {
        this.imagePersonnage.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + personnage.getUrlImage()));
    }
}

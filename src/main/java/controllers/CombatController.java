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
    private Personnage ennemi;

    @FXML
    private HBox vBoxInformations;

    @FXML
    private ImageView imagePersonnage;

    @FXML
    private ImageView imageEnnemi;

    @FXML
    private Label labelNomPersonnage;

    @FXML
    private Label labelNomEnnemi;


    public void setParent(RPG rpg) {
        this.parent = parent;
    }

    @FXML
    public void clicked() throws InterruptedException {
        //this.animationAttaqueRecu();
        this.animationAttaqueEnvoye();
        ennemi.recevoirDegats(50);
    }

    public void animationAttaqueEnvoye(){
        RotateTransition rtPerso = new RotateTransition(Duration.millis(500),imagePersonnage);
        RotateTransition rtEnnemi = new RotateTransition(Duration.millis(500),imageEnnemi);

        rtPerso.setByAngle(20);
        rtPerso.setCycleCount(2);
        rtPerso.setAutoReverse(true);
        rtPerso.setOnFinished(e->{
            imagePersonnage.setRotate(0);
            imagePersonnage.setTranslateX(0);
            imagePersonnage.setTranslateY(0);
        });

        rtEnnemi.setByAngle(20);
        rtEnnemi.setCycleCount(2);
        rtEnnemi.setAutoReverse(true);
        rtEnnemi.setOnFinished(e->{
            imageEnnemi.setRotate(0);
            imageEnnemi.setTranslateX(0);
            imageEnnemi.setTranslateY(0);
        });

        TranslateTransition ttPerso = new TranslateTransition(Duration.millis(500),imagePersonnage);
        TranslateTransition ttEnnemi = new TranslateTransition(Duration.millis(500),imageEnnemi);

        ttPerso.setByX(400);
        ttPerso.setByY(-20);
        ttPerso.setCycleCount(2);
        ttPerso.setAutoReverse(true);

        ttEnnemi.setByX(50);
        ttEnnemi.setByY(-20);
        ttEnnemi.setCycleCount(2);
        ttEnnemi.setAutoReverse(true);

        ttPerso.play();
        rtPerso.play();
        ttEnnemi.play();
        rtEnnemi.play();
    }

    // chiant d'avoir la meme fonction juste avec des valeurs differents pour inverser les roles
    // faudrait refactor car code moche

    public void animationAttaqueRecu(){
        RotateTransition rtPerso = new RotateTransition(Duration.millis(500),imagePersonnage);
        RotateTransition rtEnnemi = new RotateTransition(Duration.millis(500),imageEnnemi);

        rtPerso.setByAngle(-20);
        rtPerso.setCycleCount(2);
        rtPerso.setAutoReverse(true);
        rtPerso.setOnFinished(e->{
            imagePersonnage.setRotate(0);
            imagePersonnage.setTranslateX(0);
            imagePersonnage.setTranslateY(0);
        });

        rtEnnemi.setByAngle(-20);
        rtEnnemi.setCycleCount(2);
        rtEnnemi.setAutoReverse(true);
        rtEnnemi.setOnFinished(e->{
            imageEnnemi.setRotate(0);
            imageEnnemi.setTranslateX(0);
            imageEnnemi.setTranslateY(0);
        });

        TranslateTransition ttPerso = new TranslateTransition(Duration.millis(500),imagePersonnage);
        TranslateTransition ttEnnemi = new TranslateTransition(Duration.millis(500),imageEnnemi);

        ttPerso.setByX(-50);
        ttPerso.setByY(-20);
        ttPerso.setCycleCount(2);
        ttPerso.setAutoReverse(true);

        ttEnnemi.setByX(-500);
        ttEnnemi.setByY(-20);
        ttEnnemi.setCycleCount(2);
        ttEnnemi.setAutoReverse(true);

        ttPerso.play();
        rtPerso.play();
        ttEnnemi.play();
        rtEnnemi.play();
    }

    public void initialiser(Personnage personnage, Personnage ennemi) {
        this.personnage = personnage;
        this.ennemi =ennemi;
        this.labelNomPersonnage.setText(personnage.getNom());
        this.labelNomEnnemi.setText(ennemi.getNom());

        this.chargerImage();
        try {
            FXMLLoader loaderInfoPersonnage = new FXMLLoader();
            FXMLLoader loaderInfoEnnemi = new FXMLLoader();

            loaderInfoPersonnage.setLocation(getClass().getResource("../views/InfoPersonnage.fxml"));
            loaderInfoEnnemi.setLocation(getClass().getResource("../views/InfoEnnemi.fxml"));

            GridPane infosPerso = loaderInfoPersonnage.load();
            GridPane infosEnnemi = loaderInfoEnnemi.load();

            InfoPersonnageController controllerInfoPersonnage = loaderInfoPersonnage.getController();
            InfoPersonnageController controllerInfoEnnemi = loaderInfoEnnemi.getController();

            controllerInfoPersonnage.setPersonnage(this.personnage);
            controllerInfoEnnemi.setPersonnage(this.ennemi);

            this.vBoxInformations.getChildren().add(infosPerso);
            this.vBoxInformations.getChildren().add(infosEnnemi);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chargerImage() {
        this.imagePersonnage.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + personnage.getUrlImage()));
        this.imageEnnemi.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + ennemi.getUrlImage()));

    }
}

package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import main.java.RPG;
import main.java.models.Personnage;

import java.io.IOException;

public class CombatController {
    private RPG parent;
    private Personnage personnage;

    @FXML
    private HBox vBoxInformations;

    @FXML
    private ImageView imagePersonnage;

    public void setParent(RPG rpg) {
        this.parent = parent;
    }

    @FXML
    public void clicked() {
        System.out.println(imagePersonnage.getImage().getWidth());
    }

    public void initialiser(Personnage personnage) {
        this.personnage = personnage;
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
        this.imagePersonnage.setImage(new Image(personnage.getImage()));
    }
}

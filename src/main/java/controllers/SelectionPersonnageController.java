package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.java.RPG;
import main.java.utils.Constante;

import java.io.File;

public class SelectionPersonnageController {
    private RPG parent;

    @FXML
    private TextField pseudo;

    @FXML
    private ComboBox<String> choixEnnemis;

    @FXML
    private Button btnCharger;

    @FXML
    public void selectionnerArcher() {
        parent.selectionnerArcher(this.pseudo.getText());
    }

    @FXML
    public void selectionnerGuerrier() {
        parent.selectionnerGuerrier(this.pseudo.getText());
    }

    @FXML
    public void selectionnerMage() {
        parent.selectionnerMage(this.pseudo.getText());
    }

    public void setParent(RPG parent) {
        this.parent = parent;
    }

    public String choixEnnemis() {
        return this.choixEnnemis.getValue();
    }

    @FXML
    public void initialize() {
        this.choixEnnemis.getItems().addAll("débutant", "intermédiaire");
        this.choixEnnemis.getSelectionModel().select("débutant");
        choixEnnemis.setOnAction(e -> {
            System.out.println(this.choixEnnemis.getValue());
        });
        File sauvegarde = new File(Constante.CHEMIN_IMAGE + "sauvegarde.json");
        if (!sauvegarde.isFile())
            this.btnCharger.setDisable(true);
    }

    @FXML
    public void charger() {
        parent.charger();
    }
}

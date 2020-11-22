package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import main.java.models.Personnage;

public class InfoPersonnageController {
    private Personnage personnage;
    
    @FXML
    private Text textPv;
    @FXML
    private Text textPm;
    @FXML
    private Text textLvl;

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }
}

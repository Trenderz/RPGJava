package main.java.controllers;

import javafx.fxml.FXML;
import main.java.RPG;

public class SelectionPersonnageController {
    private RPG parent;

    @FXML
    public void selectionnerArcher(){
        parent.selectionnerArcher();
    }

    @FXML
    public void selectionnerGuerrier(){
        parent.selectionnerGuerrier();
    }

    @FXML
    public void selectionnerMage(){
        parent.selectionnerMage();
    }

    public void setParent(RPG parent){
        this.parent = parent;
    }
}

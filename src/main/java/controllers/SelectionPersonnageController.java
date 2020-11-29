package main.java.controllers;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import main.java.RPG;

public class SelectionPersonnageController {
    private RPG parent;
    private Stage primaryStage;

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

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}

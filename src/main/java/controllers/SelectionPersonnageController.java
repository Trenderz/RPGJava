package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.RPG;

public class SelectionPersonnageController {
    private RPG parent;
    private Stage primaryStage;

    @FXML
    private TextField pseudo;

    @FXML
    private ComboBox<String> choixEnnemis;

    @FXML
    public void selectionnerArcher(){
        parent.selectionnerArcher(this.pseudo.getText() );
    }

    @FXML
    public void selectionnerGuerrier(){
        parent.selectionnerGuerrier(this.pseudo.getText());
    }

    @FXML
    public void selectionnerMage(){
        parent.selectionnerMage(this.pseudo.getText());
    }

    public void setParent(RPG parent){
        this.parent = parent;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public String choixEnnemis(){
        return this.choixEnnemis.getValue();
    }

    @FXML
    public void initialize() {
        this.choixEnnemis.getItems().removeAll(this.choixEnnemis.getItems());
        this.choixEnnemis.getItems().addAll("débutant", "intermédiaire");
        this.choixEnnemis.getSelectionModel().select("débutant");
    }

    @FXML
    public void charger(){
        parent.charger();
    }
}

package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class ConsoleController {

    @FXML
    private TextArea console;

    //initialisation de la console
    public void initialize() {
        this.console.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    console.setScrollTop(0);   //scroll la texte area au plus bas
                });
    }

    public void ajouterTexte(String texte) {
        console.appendText(texte + "\n");
    }
}

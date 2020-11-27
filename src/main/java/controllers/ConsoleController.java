package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class ConsoleController {

    @FXML
    TextArea console;

    public void initialize() {
        this.console.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    console.setScrollTop(0);   //down
                });
    }

    public void ajouterTexte(String texte) {
        console.appendText(texte + "\n");
    }
}

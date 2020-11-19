package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.java.RPG;

public class RPGController {
    @FXML
    private TextField textField;

    private RPG parent;

    public void setParent(RPG parent){
        this.parent = parent;
    }

    @FXML
    public void clicked(){
        parent.afficheConsole(textField.getText());
    }


}

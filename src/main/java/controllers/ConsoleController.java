package main.java.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ConsoleController {
    @FXML
    private TextFlow textFlow;



    public void afficher(String text) {
        if (null == textFlow)
            System.out.println("fff");
        textFlow.getChildren().add(new Text(text + "\n"));
    }
}

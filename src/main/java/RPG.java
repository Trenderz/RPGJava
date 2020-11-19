package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import main.java.controllers.ConsoleController;
import main.java.controllers.RPGController;

public class RPG extends Application {

    BorderPane rootLayout;
    ConsoleController console;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/RPG.fxml"));
        rootLayout = loader.load();
        RPGController controller = loader.getController();
        controller.setParent(this);
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/Console.fxml"));
        TextFlow tf = loader.load();
        rootLayout.setLeft(tf);
        console = loader.getController();

        primaryStage.setTitle("RPG");
        primaryStage.setScene(new Scene(rootLayout, 1000, 1100));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void afficheConsole(String text) {
        console.afficher(text);
    }
}

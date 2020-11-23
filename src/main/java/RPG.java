package main.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.controllers.CombatController;
import main.java.controllers.SelectionPersonnageController;
import main.java.models.Archer;
import main.java.models.Guerrier;
import main.java.models.Mage;
import main.java.models.Personnage;
import main.java.utils.Constante;

import java.io.IOException;


public class RPG extends Application {

    private Pane rootLayout;
    private SelectionPersonnageController selectionPersonnageController;
    private Personnage personnage;
    private CombatController combat;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/SelectionPersonnage.fxml"));
        rootLayout = loader.load();
        selectionPersonnageController = loader.getController();
        selectionPersonnageController.setParent(this);
        stage = primaryStage;
        primaryStage.setTitle("RPG");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:"+ Constante.CHEMIN_IMAGE+"icone.jpg"));
        primaryStage.setScene(new Scene(rootLayout, 1200, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void selectionnerArcher() {
        personnage = new Archer();
        afficherCombat();
    }

    public void selectionnerGuerrier() {
        personnage = new Guerrier();
        afficherCombat();
    }

    public void selectionnerMage() {
        personnage = new Mage();
        afficherCombat();
    }

    private void afficherCombat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/Combat.fxml"));
            rootLayout = loader.load();
            combat = loader.getController();
            combat.setParent(this);
            combat.initialiser(this.personnage);
            stage.setScene(new Scene(rootLayout,1200,800));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

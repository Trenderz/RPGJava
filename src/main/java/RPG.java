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

import java.io.*;


public class RPG extends Application {

    private Pane rootLayout;
    private SelectionPersonnageController selectionPersonnageController;
    private Personnage personnage;
    private CombatController combat;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("views/SelectionPersonnage.fxml"));
        rootLayout = loader.load();
        selectionPersonnageController = loader.getController();
        selectionPersonnageController.setParent(this);
        primaryStage.setTitle("RPG");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:"+ Constante.CHEMIN_IMAGE+"icone.jpg"));
        primaryStage.setScene(new Scene(rootLayout, 1200, 800));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void selectionnerArcher(String pseudo) {
        personnage = new Archer();
        personnage.setNom(pseudo);
        afficherCombat();
    }

    public void selectionnerGuerrier(String pseudo) {
        personnage = new Guerrier();
        personnage.setNom(pseudo);
        afficherCombat();
    }

    public void selectionnerMage(String pseudo) {
        personnage = new Mage();
        personnage.setNom(pseudo);
        afficherCombat();
    }


    private void afficherCombat() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/Combat.fxml"));
            rootLayout = loader.load();
            combat = loader.getController();
            combat.setParent(this);
            combat.setPrimaryStage(stage);
            combat.setFichierEnnemis(selectionPersonnageController.choixEnnemis());
            combat.initialiser(this.personnage);
            stage.setScene(new Scene(rootLayout,1200,800));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void retourEcranSelection() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("views/SelectionPersonnage.fxml"));
            Pane pane = loader.load();

            SelectionPersonnageController controllerPersonnage = loader.getController();
            controllerPersonnage.setParent(this);
            controllerPersonnage.setPrimaryStage(stage);
            this.stage.setScene(new Scene(pane,1200,800));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getStage() {
        return this.stage;
    }


}

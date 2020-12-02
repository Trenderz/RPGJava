package main.java;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.java.controllers.CombatController;
import main.java.controllers.SelectionPersonnageController;
import main.java.models.*;
import main.java.utils.Constante;
import org.hildan.fxgson.FxGson;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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

    public void charger(){
        this.personnage = this.chargerJson(Constante.CHEMIN_IMAGE+"sauvegarde.json");
        this.afficherCombat();
    }

    public Personnage chargerJson(String adresseFichier) {
        Gson g = FxGson.coreBuilder().registerTypeAdapter(Arme.class,new InterfaceAdapter()).registerTypeAdapter(Personnage.class,new InterfaceAdapter()).create();
        Personnage personnage = null;
        InputStream is;
        try {
            is = new FileInputStream(new File(adresseFichier));
            // Creation du JsonReader depuis Json.
            JsonReader reader = Json.createReader(is);
            // Recuperer la structure JsonObject depuis le JsonReader.
            JsonObject objetJson = reader.readObject();
            reader.close();
            personnage = g.fromJson(objetJson.toString(), Personnage.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personnage;
    }
}

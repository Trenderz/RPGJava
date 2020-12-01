package main.java.controllers;

import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.java.RPG;
import main.java.models.*;
import main.java.models.armes.Hache;
import main.java.utils.Constante;

import java.io.*;

public class CombatController {
    private RPG parent;
    private Personnage personnage;
    private Personnage ennemi;
    private int numEnnemi = 0;
    private ConsoleController consoleController;
    private InfoPersonnageController controllerInfoPersonnage;
    private InfoEnnemiController controllerInfoEnnemi;


    private Stage selectionEquipement;
    private Stage finCombat;
    private Stage primaryStage;

    @FXML
    private HBox vBoxInformations;

    @FXML
    private ImageView imagePersonnage;

    @FXML
    private ImageView imageEnnemi;

    @FXML
    private Label labelNomPersonnage;

    @FXML
    private Label labelNomEnnemi;



    public void setParent(RPG rpg) {
        this.parent = rpg;
    }

    public void animationAttaqueEnvoye(){
        RotateTransition rtPerso = new RotateTransition(Duration.millis(500),imagePersonnage);
        RotateTransition rtEnnemi = new RotateTransition(Duration.millis(500),imageEnnemi);

        rtPerso.setByAngle(20);
        rtPerso.setCycleCount(2);
        rtPerso.setAutoReverse(true);
        rtPerso.setOnFinished(e->{
            imagePersonnage.setRotate(0);
            imagePersonnage.setTranslateX(0);
            imagePersonnage.setTranslateY(0);
        });

        rtEnnemi.setByAngle(20);
        rtEnnemi.setCycleCount(2);
        rtEnnemi.setAutoReverse(true);
        rtEnnemi.setOnFinished(e->{
            imageEnnemi.setRotate(0);
            imageEnnemi.setTranslateX(0);
            imageEnnemi.setTranslateY(0);
        });

        TranslateTransition ttPerso = new TranslateTransition(Duration.millis(500),imagePersonnage);
        TranslateTransition ttEnnemi = new TranslateTransition(Duration.millis(500),imageEnnemi);

        ttPerso.setByX(400);
        ttPerso.setByY(-20);
        ttPerso.setCycleCount(2);
        ttPerso.setAutoReverse(true);

        ttEnnemi.setByX(50);
        ttEnnemi.setByY(-20);
        ttEnnemi.setCycleCount(2);
        ttEnnemi.setAutoReverse(true);

        ttPerso.play();
        rtPerso.play();
        ttEnnemi.play();
        rtEnnemi.play();

        PauseTransition p = new PauseTransition(Duration.millis(1200));
        p.play();
        p.setOnFinished(event -> {
            this.attaqueEnnemi();
        });
    }

    // chiant d'avoir la meme fonction juste avec des valeurs differents pour inverser les roles
    // faudrait refactor car code moche

    public void animationAttaqueRecu(){
        RotateTransition rtPerso = new RotateTransition(Duration.millis(500),imagePersonnage);
        RotateTransition rtEnnemi = new RotateTransition(Duration.millis(500),imageEnnemi);

        rtPerso.setByAngle(-20);
        rtPerso.setCycleCount(2);
        rtPerso.setAutoReverse(true);
        rtPerso.setOnFinished(e->{
            imagePersonnage.setRotate(0);
            imagePersonnage.setTranslateX(0);
            imagePersonnage.setTranslateY(0);
        });

        rtEnnemi.setByAngle(-20);
        rtEnnemi.setCycleCount(2);
        rtEnnemi.setAutoReverse(true);
        rtEnnemi.setOnFinished(e->{
            this.personnage.regenPm();
            imageEnnemi.setRotate(0);
            imageEnnemi.setTranslateX(0);
            imageEnnemi.setTranslateY(0);
        });

        TranslateTransition ttPerso = new TranslateTransition(Duration.millis(500),imagePersonnage);
        TranslateTransition ttEnnemi = new TranslateTransition(Duration.millis(500),imageEnnemi);

        ttPerso.setByX(-50);
        ttPerso.setByY(-20);
        ttPerso.setCycleCount(2);
        ttPerso.setAutoReverse(true);

        ttEnnemi.setByX(-500);
        ttEnnemi.setByY(-20);
        ttEnnemi.setCycleCount(2);
        ttEnnemi.setAutoReverse(true);

        ttPerso.play();
        rtPerso.play();
        ttEnnemi.play();
        rtEnnemi.play();

    }

    public void initialiser(Personnage personnage) {
        this.personnage = personnage;
        this.labelNomPersonnage.setText(personnage.getNom());
        this.imagePersonnage.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + personnage.getUrlImage()));

        try {
            FXMLLoader loaderInfoPersonnage = new FXMLLoader();
            FXMLLoader loaderConsole = new FXMLLoader();
            FXMLLoader loaderInfoEnnemi = new FXMLLoader();


            loaderInfoPersonnage.setLocation(getClass().getResource("../views/InfoPersonnage.fxml"));
            loaderConsole.setLocation(getClass().getResource("../views/Console.fxml"));
            loaderInfoEnnemi.setLocation(getClass().getResource("../views/InfoEnnemi.fxml"));

            AnchorPane infosPerso = loaderInfoPersonnage.load();
            Pane console = loaderConsole.load();
            AnchorPane infosEnnemi = loaderInfoEnnemi.load();

            controllerInfoPersonnage = loaderInfoPersonnage.getController();
            consoleController = loaderConsole.getController();
            controllerInfoEnnemi = loaderInfoEnnemi.getController();


            controllerInfoPersonnage.setPersonnage(this.personnage);
            controllerInfoPersonnage.setParent(this);
            controllerInfoEnnemi.setParent(this);

            try {
                this.chargerEnnemi();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.labelNomEnnemi.setText(ennemi.getNom());


            this.vBoxInformations.getChildren().add(infosPerso);
            this.vBoxInformations.getChildren().add(console);
            this.vBoxInformations.getChildren().add(infosEnnemi);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action1() {
        if (this.personnage.getPm() >= this.personnage.getCoutManaAction1()){
            this.animationAttaqueEnvoye();
            consoleController.ajouterTexte(personnage.getNom() + personnage.getNomAction1());
            consoleController.ajouterTexte(ennemi.getNom() + " subit " + personnage.getDegatsAction1() + " dégats\n");
        }else {
            consoleController.ajouterTexte("action impossible, pas assez de pm");
        }
        this.personnage.action1(ennemi);
        controllerInfoPersonnage.updateInfosPerso();
    }

    public void action2() {
        if (this.personnage.getPm() >= this.personnage.getSortEquipe().getCoutMana()){
            this.animationAttaqueEnvoye();
            consoleController.ajouterTexte(personnage.getNom() + " lance " + personnage.getSortEquipe().getNom());
            consoleController.ajouterTexte(ennemi.getNom() + " subit " + personnage.getSortEquipe().getNbDegats() + " dégats\n");
        }else {
            consoleController.ajouterTexte("action impossible, pas assez de pm");
        }
        this.personnage.action2(ennemi);
    }


    public void passerTour() {
        consoleController.ajouterTexte("Tour passé\n");
        this.attaqueEnnemi();
    }

    public void attaqueEnnemi() {
        if (ennemi.getPv() > 0) {
            if (ennemi.getPm() >= ennemi.getCoutManaAction2()) {
                ennemi.action2(personnage);
                this.animationAttaqueRecu();
                consoleController.ajouterTexte(ennemi.getNom() + " lance " + ennemi.getSortEquipe().getNom());
                consoleController.ajouterTexte(personnage.getNom() + " subit " + ennemi.getSortEquipe().getNbDegats() + " dégats\n");
            } else if (ennemi.getPm() >= ennemi.getCoutManaAction1()) {
                ennemi.action1(personnage);
                this.animationAttaqueRecu();
                consoleController.ajouterTexte(ennemi.getNom() + ennemi.getNomAction1());
                consoleController.ajouterTexte(personnage.getNom() + " subit " + ennemi.getDegatsAction1() + " dégats\n");
            }else{
                consoleController.ajouterTexte(ennemi.getNom() + " ne possède pas assez \nde pm pour attaquer");
            }
        }
    }

    public void changerEquipement() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/SelectionEquipement.fxml"));
            TabPane pane = loader.load();

            Scene scene = new Scene(pane);
            this.selectionEquipement = new Stage();
            SelectionEquipementController controllerEquipement = loader.getController();
            controllerEquipement.initialiserEquipement(this.personnage);
            controllerEquipement.setParent(this);
            this.selectionEquipement.setScene(scene);
            this.selectionEquipement.setTitle("Selectionner votre Equipement");
            this.selectionEquipement.initOwner(primaryStage);
            this.selectionEquipement.initModality(Modality.WINDOW_MODAL);
            this.selectionEquipement.getIcons().add(new Image("file:" + Constante.CHEMIN_IMAGE + "icone_changer_equipement.png"));

            this.selectionEquipement.showAndWait();
            controllerInfoPersonnage.chargerImagesActions();
            controllerInfoPersonnage.updateInfosPerso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void chargerEnnemi() throws IOException {
        File fichierEnnemis = new File( Constante.CHEMIN_IMAGE+"ennemis.txt");
        BufferedReader reader = new BufferedReader(new FileReader(fichierEnnemis));
        String line =" ";
        for (int i =0; i <= numEnnemi;i++){
            line = reader.readLine();
        }

        String[] lineSplit = line.split(",");

        String nom = lineSplit[0];
        float pv = Float.parseFloat(lineSplit[1]);
        float pm = Float.parseFloat(lineSplit[2]);
        float regenPm = Float.parseFloat(lineSplit[3]);
        int niv = Integer.parseInt(lineSplit[4]);
        String urlImage = lineSplit[5];
        String classe = lineSplit[6];

        switch(classe) {
            case "guerrier":
                this.ennemi = new Guerrier(nom,pv, pm, regenPm, niv, urlImage);
                if(this.ennemi.getNiv() >= 10){
                    this.ennemi.equiperArme(new Hache());
                }
                break;
            case "archer":
                this.ennemi = new Archer(nom,pv, pm, regenPm, niv, urlImage);
                break;
            case "mage":
                this.ennemi = new Mage(nom,pv, pm, regenPm, niv, urlImage);
                break;
            default:
                // code block
        }
        this.imageEnnemi.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.ennemi.getUrlImage()));
        this.labelNomEnnemi.setText(ennemi.getNom());

        controllerInfoEnnemi.setPersonnage(this.ennemi);

        this.numEnnemi++;
    }

    public void gameOver() {
        this.parent.gameOver();
    }

    public void ennemiVaincu(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/FinCombat.fxml"));
            TabPane pane = loader.load();

            Scene scene = new Scene(pane);
            this.finCombat = new Stage();

            FinCombatController finCombatController = loader.getController();
            finCombatController.setParent(this);
            finCombatController.init(personnage,ennemi);

            this.finCombat.setScene(scene);
            this.finCombat.setTitle("Récompenses ");
            this.finCombat.initOwner(primaryStage);
            this.finCombat.initModality(Modality.WINDOW_MODAL);
            this.finCombat.getIcons().add(new Image("file:" + Constante.CHEMIN_IMAGE + "icone_changer_equipement.png"));

            this.finCombat.showAndWait();
            this.personnage.setNiv(this.personnage.getNiv() + 1);
            this.personnage.setPvMax(this.personnage.getPvMax()+50);
            this.personnage.setPmMax(this.personnage.getPmMax()+10);
            this.personnage.setPv(this.personnage.getPvMax());
            this.personnage.setPm(this.personnage.getPmMax());

            controllerInfoPersonnage.updateInfosPerso();
            consoleController.ajouterTexte("Nouvel ennemi !");
            consoleController.ajouterTexte("Santé et mana restaurés");

        } catch (IOException e) {
            e.printStackTrace();
        }
       try {
            this.chargerEnnemi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

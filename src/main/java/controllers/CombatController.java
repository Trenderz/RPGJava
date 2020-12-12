package main.java.controllers;


import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
import main.java.models.Archer;
import main.java.models.Guerrier;
import main.java.models.Mage;
import main.java.models.Personnage;
import main.java.models.armes.ArcPrecision;
import main.java.models.armes.Hache;
import main.java.models.exceptions.ManaNegatifException;
import main.java.models.exceptions.PartieFinitException;
import main.java.models.exceptions.PersonnageMortException;
import main.java.models.exceptions.PlusDeFlecheException;
import main.java.models.sorts.AttaqueLoup;
import main.java.models.sorts.TirPoison;
import main.java.utils.Constante;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CombatController {
    private RPG parent;
    private Personnage personnage;
    private Personnage ennemi;
    private int numEnnemi;
    private ConsoleController consoleController;
    private InfoPersonnageController controllerInfoPersonnage;
    private InfoEnnemiController controllerInfoEnnemi;
    private boolean ennemiRestant = true;


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

    private String fichierEnnemis;

    public void setParent(RPG rpg) {
        this.parent = rpg;
    }

    public void animationAttaqueEnvoye() {
        controllerInfoPersonnage.desacActions(); // empeche l'utilisateur d'effectuer d'autres actions durant celle en cours
        animationAttaque(imagePersonnage, imageEnnemi, -1); // animation d'attaque sur un ennemi
    }

    public void lancerAttaqueEnnemi() {
        PauseTransition p = new PauseTransition(Duration.millis(1000));
        p.play();
        p.setOnFinished(event -> this.attaqueEnnemi());
        // après l'animation d'attaque du personnage on lance celle de l'ennemi
    }

    //animation d'une attaque prenant en entrée quel personnage l'initie et lequel la recoit
    private void animationAttaque(ImageView attaquant, ImageView defenseur, int dir) {
        RotateTransition rtAttaquant = new RotateTransition(Duration.millis(500), attaquant);
        RotateTransition rtDefenseur = new RotateTransition(Duration.millis(500), defenseur);

        rotate(attaquant, rtAttaquant, dir);

        rotate(defenseur, rtDefenseur, dir);

        TranslateTransition ttAttaquant = new TranslateTransition(Duration.millis(500), attaquant);
        TranslateTransition ttDefenseur = new TranslateTransition(Duration.millis(500), defenseur);

        ttDefenseur.setByX(-50 * dir);
        ttDefenseur.setByY(-20);
        ttDefenseur.setCycleCount(2);
        ttDefenseur.setAutoReverse(true);

        ttAttaquant.setByX(-500 * dir);
        ttAttaquant.setByY(-20);
        ttAttaquant.setCycleCount(2);
        ttAttaquant.setAutoReverse(true);

        ttDefenseur.play();
        rtAttaquant.play();
        ttAttaquant.play();
        rtDefenseur.play();
    }

    private void rotate(ImageView imageView, RotateTransition rotateTransition, int i) {
        rotateTransition.setByAngle(i * (-20));
        rotateTransition.setCycleCount(2);
        rotateTransition.setAutoReverse(true);
        rotateTransition.setOnFinished(e -> {
            imageView.setRotate(0);
            imageView.setTranslateX(0);
            imageView.setTranslateY(0);
        });
    }


    public void animationAttaqueRecu() {
        animationAttaque(imageEnnemi, imagePersonnage, 1);
        PauseTransition p1 = new PauseTransition(Duration.millis(1500));
        p1.play();
        p1.setOnFinished(
                event -> {
                    controllerInfoPersonnage.activActions();
                    this.personnage.regenPm();
                    this.ennemi.regenPm();
                });
        //après l'attaque de l'ennemi on reactive les les actions du personnage
        // et on applique la regen de pm pour les 2 personnages
    }

    public void initialiser(Personnage personnage) {
        //init du personnage, de son image et de son nom a l'ecran
        this.personnage = personnage;
        this.labelNomPersonnage.setText(personnage.getNom());
        this.imagePersonnage.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + personnage.getUrlImage()));

        // ici l'on va charger les FMXL des informations du personnage et de l'ennemi ainsi que le FXML de la console
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

            try {
                this.chargerEnnemi();
            } catch (IOException | PartieFinitException e) {
                e.printStackTrace();
            }

            this.vBoxInformations.getChildren().add(infosPerso);
            this.vBoxInformations.getChildren().add(console);
            this.vBoxInformations.getChildren().add(infosEnnemi);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void action1() {
        try {
            //realistion de l'action 1 du personnage et ajout du texte à la console
            this.personnage.action1(ennemi);
            this.animationAttaqueEnvoye(); // animation de l'action
            consoleController.ajouterTexte(personnage.getNom() + personnage.getNomAction1());
            consoleController.ajouterTexte(ennemi.getNom() + " subit " + personnage.getDegatsAction1() + " dégats\n");
            lancerAttaqueEnnemi();
        } catch (PersonnageMortException e) {
            // exception si l'ennemi est vaincu
            this.animationAttaqueEnvoye();
            ennemi.setPvAZero();
            consoleController.ajouterTexte(personnage.getNom() + personnage.getNomAction1());
            consoleController.ajouterTexte(ennemi.getNom() + " subit " + personnage.getDegatsAction1() + " dégats\n");
            PauseTransition p = new PauseTransition(Duration.millis(500));
            p.play();
            p.setOnFinished(event ->ennemiVaincu());
            // appel de la fonction s'occupant des actions à realiser à la mort d'un ennemi
        } catch (ManaNegatifException e) {
            //exception si le personnage n'a pas assez de mana pour lancer son action
            consoleController.ajouterTexte("action impossible, pas assez de pm");
        } catch (PlusDeFlecheException e) {
            //exception si le personnage n'a pas plus assez de fleches pour attaquer ( pour l'archer notamment)
            consoleController.ajouterTexte("action impossible, plus de fleche");
        }
        // mise a jour des infos du persos ( principalement pour le decompte des fleches apres une attaque)
        controllerInfoPersonnage.updateInfosPerso();
    }

    public void action2() {
        // meme fonctionnement que la fonction action1()
        // informations differentes
        try {
            this.animationAttaqueEnvoye();
            this.personnage.action2(ennemi);
            consoleController.ajouterTexte(personnage.getNom() + " lance " + personnage.getSortEquipe().getNom());
            consoleController.ajouterTexte(ennemi.getNom() + " subit " + personnage.getSortEquipe().getNbDegats() + " dégats\n");
            lancerAttaqueEnnemi();
        } catch (PersonnageMortException e) {
            ennemi.setPvAZero();
            consoleController.ajouterTexte(personnage.getNom() + " lance " + personnage.getSortEquipe().getNom());
            consoleController.ajouterTexte(ennemi.getNom() + " subit " + personnage.getSortEquipe().getNbDegats() + " dégats\n");
            PauseTransition p = new PauseTransition(Duration.millis(1200));
            p.play();
            p.setOnFinished(event ->ennemiVaincu());
        } catch (ManaNegatifException e) {
            consoleController.ajouterTexte("action impossible, pas assez de pm");
        }
    }

    //permet au joueur de simplement passer son tour
    public void passerTour() {
        consoleController.ajouterTexte("Tour passé\n");
        this.attaqueEnnemi();
    }

    // attaque de l'ennemi
    // l'ennemi effectue en priorité son action 2 ( action la plus couteuse en mana )
    // s'il n'a assez de mana pour aucunes actions, il passe son tour
    public void attaqueEnnemi() {
        if (ennemi.getPm() >= ennemi.getCoutManaAction2()) {
            action2Ennemi();
        } else if (ennemi.getPm() >= ennemi.getCoutManaAction1()) {
            action1Ennemi();
        }
        this.controllerInfoEnnemi.updateInfosPerso();
    }

    private void action1Ennemi() {
        try {
            ennemi.action1(personnage);
            this.animationAttaqueRecu();
            consoleController.ajouterTexte(ennemi.getNom() + ennemi.getNomAction1());
            consoleController.ajouterTexte(personnage.getNom() + " subit " + ennemi.getDegatsAction1() + " dégats\n");
        } catch (PersonnageMortException e) {
            personnage.setPvAZero();
            personnageMort();
        } catch (ManaNegatifException e) {
            consoleController.ajouterTexte(ennemi.getNom() + " ne possède pas assez \nde pm pour attaquer");
        } catch (PlusDeFlecheException e) {
            consoleController.ajouterTexte(ennemi.getNom() + " n'a pas assez de flèches pour attaquer");
        }
    }

    private void action2Ennemi() {
        try {
            ennemi.action2(personnage);
            this.animationAttaqueRecu();
            consoleController.ajouterTexte(ennemi.getNom() + " lance " + ennemi.getSortEquipe().getNom());
            consoleController.ajouterTexte(personnage.getNom() + " subit " + ennemi.getSortEquipe().getNbDegats() + " dégats\n");
        } catch (PersonnageMortException e) {
            personnage.setPvAZero();
            personnageMort();
        } catch (ManaNegatifException e) {
            //on ne peut pas arriver ici
        }
    }

    // procedure s'occupant du game over de notre personnage
    private void personnageMort() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Game Over");
        alert.setContentText("Vous êtes mort ... ( faut le faire quand même)");
        // retout a l'ecran de selection de personnage après l'alerte
        alert.setOnHidden(evt -> parent.retourEcranSelection());
        alert.show();
    }

    //creation et affichage de la fenetre pour changer l'equipement
    public void changerEquipement() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/SelectionEquipement.fxml"));
            TabPane pane = loader.load();

            Scene scene = new Scene(pane);
            Stage selectionEquipement = new Stage();
            SelectionEquipementController controllerEquipement = loader.getController();
            controllerEquipement.initialiserEquipement(this.personnage);
            selectionEquipement.setScene(scene);
            selectionEquipement.setTitle("Selectionner votre Equipement");
            selectionEquipement.initOwner(primaryStage);
            selectionEquipement.initModality(Modality.WINDOW_MODAL);
            selectionEquipement.getIcons().add(new Image("file:" + Constante.CHEMIN_IMAGE + "icone_changer_equipement.png"));
            selectionEquipement.setOnCloseRequest(event -> {
                if (!controllerEquipement.aEquipementEtSortEquipe()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Vous n'avez pas d'arme ou/et de sort equipé !", ButtonType.CANCEL);
                    alert.show();
                    event.consume();
                }
            });

            selectionEquipement.showAndWait();
            controllerInfoPersonnage.chargerImagesActions();
            controllerInfoPersonnage.updateInfosPerso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setFichierEnnemis(String choixEnnemis) {
        this.fichierEnnemis = choixEnnemis;
    }

    public void chargerEnnemi() throws IOException, PartieFinitException {
        File fichierEnnemis = new File(Constante.CHEMIN_IMAGE + this.fichierEnnemis + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(fichierEnnemis));
        String line = " ";
        for (int i = 0; i <= numEnnemi; i++) {
            line = reader.readLine();
        }
        if (line != null) {
            String[] lineSplit = line.split(",");

            String nom = lineSplit[0];
            float pv = Float.parseFloat(lineSplit[1]);
            float pm = Float.parseFloat(lineSplit[2]);
            float regenPm = Float.parseFloat(lineSplit[3]);
            int niv = Integer.parseInt(lineSplit[4]);
            String urlImage = lineSplit[5];
            String classe = lineSplit[6];

            switch (classe) {
                case "guerrier":
                    this.ennemi = new Guerrier(nom, pv, pm, regenPm, niv, urlImage);
                    if (this.ennemi.getNiv() >= 10) {
                        this.ennemi.equiperSort(new AttaqueLoup());
                    }
                    if(this.ennemi.getNiv() >=15) this.ennemi.equiperArme(new Hache());
                    break;
                case "archer":
                    this.ennemi = new Archer(nom, pv, pm, regenPm, niv, urlImage);
                    if (this.ennemi.getNiv() >= 10) {
                        this.ennemi.equiperSort(new TirPoison());
                    }
                    if(this.ennemi.getNiv() >=15) this.ennemi.equiperArme(new ArcPrecision());
                    break;
                case "mage":
                    this.ennemi = new Mage(nom, pv, pm, regenPm, niv, urlImage);
                    break;
                default:
                    // code block
            }
            this.imageEnnemi.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.ennemi.getUrlImage()));
            this.labelNomEnnemi.setText(ennemi.getNom());

            controllerInfoEnnemi.setPersonnage(this.ennemi);
        } else {
            throw new PartieFinitException("partie finie");
        }
    }

    public void ennemiVaincu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/FinCombat.fxml"));
            TabPane pane = loader.load();

            Scene scene = new Scene(pane);
            Stage finCombat = new Stage();

            FinCombatController finCombatController = loader.getController();
            finCombatController.setParent(this);
            finCombatController.init(personnage, ennemi);

            finCombat.setScene(scene);
            finCombat.setTitle("Récompenses ");
            finCombat.initOwner(primaryStage);
            finCombat.initModality(Modality.WINDOW_MODAL);
            finCombat.getIcons().add(new Image("file:" + Constante.CHEMIN_IMAGE + "icone_changer_equipement.png"));

            this.numEnnemi++;
            this.chargerEnnemi();
            this.personnage.setNiv(this.personnage.getNiv() + 1);
            this.personnage.setPvMax(this.personnage.getPvMax() + 50);
            this.personnage.setPmMax(this.personnage.getPmMax() + 10);
            this.personnage.setPvAMax();
            this.personnage.setPmAMax();
            if (personnage instanceof Archer) {
                Archer archer = (Archer) personnage;
                archer.remplirFleche();
            }
            if (ennemiRestant)finCombat.show();
            finCombat.setOnCloseRequest(e->{
                controllerInfoPersonnage.updateInfosPerso();
                consoleController.ajouterTexte("Nouvel ennemi !");
                consoleController.ajouterTexte("Santé et mana restaurés");
                controllerInfoPersonnage.activActions();

            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (PartieFinitException  e) {
            this.ennemiRestant= false;
            Alert alert = new Alert(Alert.AlertType.NONE, " ", ButtonType.OK);
            alert.setContentText("Bravo tu es parvenu à bout de tous ces ennemis, si tu as vaincu sans difficultés, " +
                    "songe à l'augmenter lors de ta prochaine partie");
            alert.show();
            parent.retourEcranSelection();
        }
    }

    public int getNumEnnemi() {
        return numEnnemi;
    }

    public String getFichierEnnemis() {
        return fichierEnnemis;
    }

    public void setNumEnnemi(int numEnnemi) {
        this.numEnnemi = numEnnemi;
    }
}



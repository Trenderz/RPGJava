package main.java.controllers;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.models.Arme;
import main.java.models.InterfaceAdapter;
import main.java.models.Personnage;
import main.java.models.Sort;
import main.java.models.armes.ArcPrecision;
import main.java.models.armes.BouclierEtincelant;
import main.java.models.armes.Epee;
import main.java.models.armes.Hache;
import main.java.models.sorts.AttaqueLoup;
import main.java.models.sorts.BouleDeFeu;
import main.java.models.sorts.Foudre;
import main.java.models.sorts.TirPoison;
import main.java.utils.Constante;
import org.hildan.fxgson.FxGson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FinCombatController {

    private CombatController parent;
    private Personnage personnage;

    @FXML
    private Text texteSauvegarde;

    @FXML
    private Label piecesRemportees;

    @FXML
    private Label piecesPersonnage1;
    @FXML
    private Label piecesPersonnage2;
    @FXML
    private Label piecesPersonnage3;

    // tab boutique arme
    @FXML
    private Label infoArmesEquipables;

    @FXML
    private ImageView imageArme1;
    @FXML
    private Label nomArme1;
    @FXML
    private Button btnArme1;

    @FXML
    private ImageView imageArme2;
    @FXML
    private Label nomArme2;
    @FXML
    private Button btnArme2;

    @FXML
    private ImageView imageArme3;
    @FXML
    private Label nomArme3;
    @FXML
    private Button btnArme3;

    @FXML
    private ImageView imageArme4;
    @FXML
    private Label nomArme4;
    @FXML
    private Button btnArme4;

    // tab boutique sort
    @FXML
    private ImageView imageSort1;
    @FXML
    private Label nomSort1;
    @FXML
    private Button btnSort1;

    @FXML
    private ImageView imageSort2;
    @FXML
    private Label nomSort2;
    @FXML
    private Button btnSort2;

    @FXML
    private ImageView imageSort3;
    @FXML
    private Label nomSort3;
    @FXML
    private Button btnSort3;

    @FXML
    private ImageView imageSort4;
    @FXML
    private Label nomSort4;
    @FXML
    private Button btnSort4;

    private final Sort sort1 = new BouleDeFeu();
    private final Sort sort2 = new AttaqueLoup();
    private final Sort sort3 = new TirPoison();
    private final Sort sort4 = new Foudre();

    private final Arme arme1 = new Hache();
    private final Arme arme2 = new ArcPrecision();
    private final Arme arme3 = new BouclierEtincelant();
    private final Arme arme4 = new Epee();


    public void setParent(CombatController parent) {
        this.parent = parent;
    }

    public void init(Personnage personnage, Personnage ennemi) {
        this.personnage = personnage;
        this.texteSauvegarde.setVisible(false);
        float pieces = ennemi.getNiv() * 25;
        this.personnage.setPieces(this.personnage.getPieces() + pieces);
        this.piecesRemportees.setText("+ " + pieces + " pièces");
        this.piecesPersonnage1.setText(this.personnage.getPieces() + " pièces au total");
        this.piecesPersonnage2.setText(this.personnage.getPieces() + " pièces au total");
        this.piecesPersonnage3.setText(this.personnage.getPieces() + " pièces au total");
        this.infoArmesEquipables.setText(this.personnage.infoArmesEquipables());
        this.chargerSort();
        this.chargerArme();
    }

    public void changerEquipement() {
        parent.changerEquipement();
    }

    public void chargerSort() {
        this.imageSort1.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort1.getUrlImage()));
        this.imageSort2.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort2.getUrlImage()));
        this.imageSort3.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort3.getUrlImage()));
        this.imageSort4.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.sort4.getUrlImage()));

        this.nomSort1.setText(this.sort1.getNom());
        this.nomSort2.setText(this.sort2.getNom());
        this.nomSort3.setText(this.sort3.getNom());
        this.nomSort4.setText(this.sort4.getNom());

        this.btnSort1.setText(this.sort1.getPrix() + " pièces");
        this.btnSort2.setText(this.sort2.getPrix() + " pièces");
        this.btnSort3.setText(this.sort3.getPrix() + " pièces");
        this.btnSort4.setText(this.sort4.getPrix() + " pièces");
    }

    public void chargerArme() {
        this.imageArme1.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.arme1.getUrlImage()));
        this.imageArme2.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.arme2.getUrlImage()));
        this.imageArme3.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.arme3.getUrlImage()));
        this.imageArme4.setImage(new Image("file:" + Constante.CHEMIN_IMAGE + this.arme4.getUrlImage()));

        this.nomArme1.setText(this.arme1.getNom());
        this.nomArme2.setText(this.arme2.getNom());
        this.nomArme3.setText(this.arme3.getNom());
        this.nomArme4.setText(this.arme4.getNom());

        this.btnArme1.setText(this.arme1.getPrix() + " pièces");
        this.btnArme2.setText(this.arme2.getPrix() + " pièces");
        this.btnArme3.setText(this.arme3.getPrix() + " pièces");
        this.btnArme4.setText(this.arme4.getPrix() + " pièces");
    }

    @FXML
    public void achatSort1() {
        achatSort(sort1);
    }

    @FXML
    public void achatSort2() {
        achatSort(sort2);
    }

    @FXML
    public void achatSort3() {
        achatSort(sort3);
    }

    @FXML
    public void achatSort4() {
        achatSort(sort4);
    }

    private void achatSort(Sort sort) {
        Alert alert = new Alert(Alert.AlertType.NONE, " ", ButtonType.OK);
        if (!this.personnage.getListeSorts().contains(sort)) {
            if (this.personnage.getPieces() >= sort.getPrix()) {
                this.personnage.ajouterSort(sort);
                this.personnage.setPieces(this.personnage.getPieces() - sort.getPrix());
                alert.setContentText(sort.getNom() + " acheté et ajouté à votre inventaire");
                this.piecesPersonnage1.setText(this.personnage.getPieces() + " pièces au total");
                this.piecesPersonnage2.setText(this.personnage.getPieces() + " pièces au total");
                this.piecesPersonnage3.setText(this.personnage.getPieces() + " pièces au total");
            } else {
                alert.setContentText("arrêtes t'as pas assez d'or ... =(");
            }
        } else {
            alert.setContentText(" vous possédez déja cette arme");
        }
        alert.show();
    }

    @FXML
    public void achatArme1() {
        achatArme(arme1);
    }

    @FXML
    public void achatArme2() {
        achatArme(arme2);
    }

    @FXML
    public void achatArme3() {
        achatArme(arme3);
    }

    @FXML
    public void achatArme4() {
        achatArme(arme4);
    }

    private void achatArme(Arme arme) {
        Alert alert = new Alert(Alert.AlertType.NONE, " ", ButtonType.OK);
        if (!this.personnage.getListeArmes().contains(arme)) {
            if (this.personnage.getPieces() >= arme.getPrix()) {
                this.personnage.ajouterArme(arme);
                this.personnage.setPieces(this.personnage.getPieces() - arme.getPrix());
                alert.setContentText(arme.getNom() + " acheté et ajouté à votre inventaire");
                this.piecesPersonnage1.setText(this.personnage.getPieces() + " pièces au total");
                this.piecesPersonnage2.setText(this.personnage.getPieces() + " pièces au total");
                this.piecesPersonnage3.setText(this.personnage.getPieces() + " pièces au total");
            } else {
                alert.setContentText("Arrêtes t'as pas assez d'or ... =(");
            }
        } else {
            alert.setContentText("Vous possédez déja cette arme");
        }
        alert.show();
    }

    @FXML
    public void sauvegarder() {
        sauvegarderJson(Constante.CHEMIN_IMAGE + "sauvegarde.json");
        sauvegarderEnnemis(parent.getNumEnnemi(), parent.getFichierEnnemis());
        texteSauvegarde.setVisible(true);
    }

    public void sauvegarderJson(String adresseFichier) {
        Gson gson = FxGson.coreBuilder().registerTypeAdapter(Arme.class, new InterfaceAdapter()).registerTypeAdapter(Personnage.class, new InterfaceAdapter()).setPrettyPrinting().create();
        String s = gson.toJson(this.personnage);

        //pour contourner un probleme de la bibliotheque Gson on ajoute a la main cette ligne
        StringBuilder builder = new StringBuilder();
        builder.append(s, 0, s.length() - 2);
        builder.append("," + "\n" + "  \"CLASS_META_KEY\": \"").append(this.personnage.getClass().getCanonicalName()).append("\"\n").append("}");
        FileWriter f;
        try {
            f = new FileWriter(adresseFichier);
            f.write(builder.toString());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sauvegarderEnnemis(int numEnnemi, String adresseFichierEnnemis) {
        File sauvegardeEnnemis = new File(Constante.CHEMIN_IMAGE + "sauvegardeEnnemis.txt");
        try {
            FileWriter f = new FileWriter(sauvegardeEnnemis);
            f.write(numEnnemi + ";" + adresseFichierEnnemis);
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

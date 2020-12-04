package main.java.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.java.models.Arme;
import main.java.models.Personnage;
import main.java.models.Sort;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SelectionEquipementController {
    private Personnage personnage;

    private Set<ItemEquipementSortController> itemSetSort;
    private Map<Sort, HBox> mapBoxInventaireSort;
    private Map<Sort, HBox> mapBoxEquipeSort;

    private Set<ItemEquipementArmeController> itemSetArme;
    private Map<Arme, HBox> mapBoxInventaireArme;
    private Map<Arme, HBox> mapBoxEquipeArme;

    @FXML
    private VBox vBoxInventaireSort;

    @FXML
    private VBox vBoxEquipeSort;

    @FXML
    private VBox vBoxInventaireArme;

    @FXML
    private VBox vBoxEquipeArme;

    @FXML
    public void initialize() {
        mapBoxInventaireSort = new HashMap<>();
        mapBoxEquipeSort = new HashMap<>();
        itemSetSort = new HashSet<>();

        mapBoxInventaireArme = new HashMap<>();
        mapBoxEquipeArme = new HashMap<>();
        itemSetArme = new HashSet<>();
    }

    public void initialiserEquipement(Personnage personnage) {
        this.personnage = personnage;
        personnage.getListeSorts().forEach(sort -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/ItemEquipementSort.fxml"));
                HBox item = loader.load();
                ItemEquipementSortController controller = loader.getController();
                controller.setParent(this);
                if (personnage.aSortEquipe(sort)) {
                    controller.chargerSort(sort, true);
                    controller.passerEquiper();
                    vBoxEquipeSort.getChildren().add(item);
                    mapBoxEquipeSort.put(sort, item);
                } else {
                    controller.chargerSort(sort, false);
                    vBoxInventaireSort.getChildren().add(item);
                    mapBoxInventaireSort.put(sort, item);
                }
                itemSetSort.add(controller);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        personnage.getListeArmes().forEach(arme -> {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/ItemEquipementArme.fxml"));
                HBox item = loader.load();
                ItemEquipementArmeController controller = loader.getController();
                controller.setParent(this);
                if (personnage.aArmeEquipe(arme)) {
                    controller.chargerArme(arme, true);
                    controller.passerEquiper();
                    vBoxEquipeArme.getChildren().add(item);
                    mapBoxEquipeArme.put(arme, item);
                } else {
                    controller.chargerArme(arme, false);
                    vBoxInventaireArme.getChildren().add(item);
                    mapBoxInventaireArme.put(arme, item);
                }
                itemSetArme.add(controller);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        if (personnage.getNombreEquipementsEquipable() == vBoxEquipeArme.getChildren().size()) {
            itemSetArme.forEach(ctrl -> {
                if (ctrl.estEquipe()) {
                    ctrl.desactiverSwitch();
                }
            });
        }

        if (personnage.getNombreSortsEquipable() == vBoxEquipeSort.getChildren().size()) {
            itemSetSort.forEach(ctrl -> {
                if (!ctrl.estEquipe()) {
                    ctrl.desactiverSwitch();
                }
            });
        }
    }

    public void switchSortVersEquipe(Sort sort, ItemEquipementSortController controller) {
        personnage.equiperSort(sort);
        HBox item = mapBoxInventaireSort.get(sort);
        mapBoxInventaireSort.remove(sort);
        mapBoxEquipeSort.put(sort, item);
        controller.passerEquiper();
        vBoxInventaireSort.getChildren().remove(item);
        vBoxEquipeSort.getChildren().add(item);
        if (personnage.getNombreSortsEquipable() == vBoxEquipeSort.getChildren().size()) {
            itemSetSort.forEach(ctrl -> {
                if (!ctrl.estEquipe()) {
                    ctrl.desactiverSwitch();
                }
            });
        }
    }

    public void switchSortVersInventaire(Sort sort, ItemEquipementSortController controller) {
        HBox item = mapBoxEquipeSort.get(sort);
        mapBoxEquipeSort.remove(sort);
        mapBoxInventaireSort.put(sort, item);
        controller.passerInventaire();
        vBoxEquipeSort.getChildren().remove(item);
        vBoxInventaireSort.getChildren().add(item);
        personnage.deEquipeSort(sort);
        itemSetSort.forEach(ItemEquipementSortController::activerSwitch);

    }

    public void switchArmeVersInventaire(Arme arme, ItemEquipementArmeController controller) {
        HBox item = mapBoxEquipeArme.get(arme);
        mapBoxEquipeArme.remove(arme);
        mapBoxInventaireArme.put(arme, item);
        controller.passerInventaire();
        vBoxEquipeArme.getChildren().remove(item);
        vBoxInventaireArme.getChildren().add(item);
        personnage.deEquipeArme(arme);
        itemSetArme.forEach(ItemEquipementArmeController::activerSwitch);
    }

    public void switchArmeVersEquipe(Arme arme, ItemEquipementArmeController controller) {
        if (personnage.peuxEquiperArme(arme)) {
            personnage.equiperArme(arme);
            HBox item = mapBoxInventaireArme.get(arme);
            mapBoxInventaireArme.remove(arme);
            mapBoxEquipeArme.put(arme, item);
            controller.passerEquiper();
            vBoxInventaireArme.getChildren().remove(item);
            vBoxEquipeArme.getChildren().add(item);
            if (personnage.getNombreEquipementsEquipable() == vBoxEquipeArme.getChildren().size()) {
                itemSetArme.forEach(ctrl -> {
                    if (ctrl.estEquipe()) {
                        ctrl.desactiverSwitch();
                    }
                });
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE, " Vous ne pouvez pas équipé ce type d'armes"
                    , ButtonType.OK);
            alert.show();
        }
    }

    public boolean aEquipementEtSortEquipe() {
        return personnage.getNombreSortsEquipable() == this.mapBoxEquipeSort.size() &&
                personnage.getNombreEquipementsEquipable() == this.mapBoxEquipeArme.size();
    }
}

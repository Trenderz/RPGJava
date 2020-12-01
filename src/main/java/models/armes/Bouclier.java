package main.java.models.armes;

import main.java.models.EquipementDefensif;

public class Bouclier extends EquipementDefensif {
    public Bouclier() {
        super("Bouclier", "bouclier.jpg", 10, 50);
    }

    public Bouclier(String nom, String urlImage, float reductionDegat, float prix) {
        super(nom, urlImage, reductionDegat, prix);
    }
}

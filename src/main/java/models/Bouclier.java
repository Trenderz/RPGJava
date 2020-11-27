package main.java.models;

public class Bouclier extends EquipementDefensif {
    public Bouclier() {
        super("Bouclier", "bouclier.jpg", 10);
    }

    public Bouclier(String nom, String urlImage, float reductionDegat) {
        super(nom, urlImage, reductionDegat);
    }
}

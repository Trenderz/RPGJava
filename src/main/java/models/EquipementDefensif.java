package main.java.models;

public class EquipementDefensif extends Arme {
    private final float reductionDegats;

    public EquipementDefensif(String nom, String urlImage, float reductionDegats, float prix) {
        super(nom, urlImage, prix);
        this.reductionDegats = reductionDegats;
    }

    public float getReductionDegats() {
        return reductionDegats;
    }
}

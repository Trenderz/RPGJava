package main.java.models;

public class EquipementDefensif extends Arme {
    private float reductionDegats;

    public EquipementDefensif(String nom, String urlImage, float reductionDegats) {
        super(nom, urlImage);
        this.reductionDegats = reductionDegats;
    }

    public float getReductionDegats() {
        return reductionDegats;
    }
}

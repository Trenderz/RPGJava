package main.java.models;

public class EquipementDefensif extends Arme {
    private float reductionDegats;

    public EquipementDefensif(String nom, float reductionDegats) {
        super(nom);
        this.reductionDegats = reductionDegats;
    }

    public float getReductionDegats() {
        return reductionDegats;
    }
}

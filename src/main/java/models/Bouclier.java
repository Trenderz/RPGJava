package main.java.models;

public class Bouclier extends EquipementDefensif {
    public Bouclier() {
        super("Bouclier",10);
    }

    public Bouclier(String nom,float reductionDegat){
        super(nom,reductionDegat);
    }
}

package main.java.models;

public class Sort {
    private String nom;
    private float nbDegats;
    private float coutMana;

    public Sort(String nom, float nbDegats,float coutMana) {
        this.nom = nom;
        this.nbDegats = nbDegats;
        this.coutMana = coutMana;
    }

    public float getNbDegats() {
        return this.nbDegats;
    }

    public float getCoutMana() {
        return this.coutMana;
    }
}

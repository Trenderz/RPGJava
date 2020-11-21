package main.java.models;

public class ArmeCAC extends Arme{
    private float nbDegats;

    public ArmeCAC(String nom, float nbDegats) {
        super(nom);
        this.nbDegats = nbDegats;
    }

    public float getNbDegats() {
        return nbDegats;
    }
}

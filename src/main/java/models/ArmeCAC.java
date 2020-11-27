package main.java.models;

public class ArmeCAC extends Arme{
    private float nbDegats;

    public ArmeCAC(String nom,String urlImage, float nbDegats) {
        super(nom,urlImage);
        this.nbDegats = nbDegats;
    }

    public float getNbDegats() {
        return nbDegats;
    }
}

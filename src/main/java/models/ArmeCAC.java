package main.java.models;

public class ArmeCAC extends Arme {
    private final float nbDegats;

    public ArmeCAC(String nom, String urlImage, float nbDegats, float prix) {
        super(nom, urlImage, prix);
        this.nbDegats = nbDegats;
    }

    public float getNbDegats() {
        return nbDegats;
    }
}

package main.java.models;

public class ArmeDistance extends Arme {
    private float multiplicateur;

    public ArmeDistance(String nom, String urlImage, float multiplicateur) {
        super(nom, urlImage);
        this.multiplicateur = multiplicateur;
    }

    public float getMultiplicateur() {
        return multiplicateur;
    }
}

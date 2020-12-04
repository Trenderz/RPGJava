package main.java.models;

public class ArmeDistance extends Arme {
    private final float multiplicateur;

    public ArmeDistance(String nom, String urlImage, float multiplicateur, float prix) {
        super(nom, urlImage, prix);
        this.multiplicateur = multiplicateur;
    }

    public float getMultiplicateur() {
        return multiplicateur;
    }
}

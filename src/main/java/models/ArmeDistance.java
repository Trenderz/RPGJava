package main.java.models;

public class ArmeDistance extends Arme{
    private float multiplicateur;

    public ArmeDistance(String nom,float multiplicateur) {
        super(nom);
        this.multiplicateur = multiplicateur;
    }

    public float getMultiplicateur() {
        return multiplicateur;
    }
}

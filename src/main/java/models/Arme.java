package main.java.models;

public abstract class Arme {
    String nom;
    int nbDegats;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbDegats() {
        return nbDegats;
    }

    public void setNbDegats(int nbDegats) {
        this.nbDegats = nbDegats;
    }
}

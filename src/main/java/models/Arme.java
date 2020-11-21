package main.java.models;

public abstract class Arme {
    private String nom;

    public Arme(String nom){
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}

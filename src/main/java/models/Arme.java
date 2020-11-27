package main.java.models;

public abstract class Arme {
    private String nom;
    private String urlImage;

    public Arme(String nom, String urlImage) {
        this.nom = nom;
        this.urlImage = urlImage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrlImage(){
        return urlImage;
    }
}

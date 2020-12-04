package main.java.models;

public abstract class Arme {
    private String nom;
    private final String urlImage;
    private final float prix;

    public Arme(String nom, String urlImage, float prix) {
        this.nom = nom;
        this.urlImage = urlImage;
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public float getPrix() {
        return this.prix;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Arme)) return false;
        Arme arme = (Arme) o;
        return nom.equals(arme.nom);
    }
}

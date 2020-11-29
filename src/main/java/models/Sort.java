package main.java.models;

public class Sort {
    private String nom;
    private String urlImage;
    private float nbDegats;
    private float coutMana;
    private float prix;

    public Sort(String nom, float nbDegats,float coutMana, float prix, String urlImage) {
        this.nom = nom;
        this.nbDegats = nbDegats;
        this.coutMana = coutMana;
        this.urlImage = urlImage;
        this.prix = prix;
    }

    public float getNbDegats() {
        return this.nbDegats;
    }

    public float getCoutMana() {
        return this.coutMana;
    }

    public String getNom() {
        return this.nom;
    }

    public String getUrlImage() {
        return this.urlImage;
    }

    public float getPrix(){return this.prix;}

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}

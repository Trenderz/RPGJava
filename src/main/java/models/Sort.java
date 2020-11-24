package main.java.models;

public class Sort {
    private String nom;
    private String urlImage;
    private float nbDegats;
    private float coutMana;

    public Sort(String nom, float nbDegats,float coutMana, String urlImage) {
        this.nom = nom;
        this.nbDegats = nbDegats;
        this.coutMana = coutMana;
        this.urlImage = urlImage;
    }

    public float getNbDegats() {
        return this.nbDegats;
    }

    public float getCoutMana() {
        return this.coutMana;
    }


    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}

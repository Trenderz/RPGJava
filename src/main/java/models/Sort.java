package main.java.models;

public class Sort {
    private final String nom;
    private String urlImage;
    private final float nbDegats;
    private final float coutMana;
    private final float prix;

    public Sort(String nom, float nbDegats, float coutMana, float prix, String urlImage) {
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

    public float getPrix() {
        return this.prix;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Sort)) return false;
        Sort sort = (Sort) o;
        return nom.equals(sort.nom);
    }
}
